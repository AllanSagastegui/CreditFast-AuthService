package pe.ask.creditfast.r2dbc.adapter;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.IsolationLevel;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.utils.gateways.TransactionalOperatorGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionalOperatorAdapter implements TransactionalOperatorGateway {

    private final DatabaseClient databaseClient;

    @Override
    public <T> Publisher<T> execute(Publisher<T> action) {
        return execute(action, false);
    }

    @Override
    public <T> Publisher<T> execute(Publisher<T> action, boolean readOnly) {
        return isMono(action)
                ? executeMono(Mono.from(action), readOnly)
                : executeFlux(Flux.from(action), readOnly);
    }

    public <T> Mono<T> executeMono(Mono<T> action, boolean readOnly) {
        return databaseClient.inConnection(conn ->
                readOnly ? readOnlyTx(conn, action) : writeTx(conn, action)
        );
    }

    public <T> Flux<T> executeFlux(Flux<T> action, boolean readOnly) {
        return databaseClient.inConnectionMany(conn ->
                readOnly ? readOnlyTxFlux(conn, action) : writeTxFlux(conn, action)
        );
    }

    private <T> Mono<T> writeTx(Connection conn, Mono<T> action) {
        return Mono.from(conn.beginTransaction())
                .then(action)
                .flatMap(r -> Mono.from(conn.commitTransaction()).thenReturn(r))
                .onErrorResume(e -> Mono.from(conn.rollbackTransaction()).then(Mono.error(e)));
    }

    private <T> Mono<T> readOnlyTx(Connection conn, Mono<T> action) {
        return Mono.fromRunnable(() ->
                        conn.setTransactionIsolationLevel(IsolationLevel.READ_COMMITTED))
                .then(action)
                .onErrorResume(Mono::error);
    }

    private <T> Flux<T> writeTxFlux(Connection conn, Flux<T> action) {
        return Mono.from(conn.beginTransaction())
                .thenMany(action)
                .collectList()
                .flatMapMany(results ->
                        Mono.from(conn.commitTransaction())
                                .thenMany(Flux.fromIterable(results))
                )
                .onErrorResume(e ->
                        Mono.from(conn.rollbackTransaction()).thenMany(Flux.error(e))
                );
    }

    private <T> Flux<T> readOnlyTxFlux(Connection conn, Flux<T> action) {
        return Mono.fromRunnable(() ->
                        conn.setTransactionIsolationLevel(IsolationLevel.READ_COMMITTED))
                .thenMany(action)
                .onErrorResume(Flux::error);
    }

    private boolean isMono(Object publisher) {
        return publisher instanceof Mono<?>;
    }

}
