package pe.ask.creditfast.model.utils.gateways;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionalOperatorGateway {
    <T> Publisher<T> execute(Publisher<T> action);
    <T> Publisher<T> execute(Publisher<T> action, boolean readOnly);

    default <T> Mono<T> executeMono(Mono<T> action, boolean readOnly) {
        return Mono.from(execute((Publisher<T>) action, readOnly));
    }

    default <T> Flux<T> executeFlux(Flux<T> action, boolean readOnly) {
        return Flux.from(execute((Publisher<T>) action, readOnly));
    }
}
