package pe.ask.creditfast.model.utils.gateways;

import reactor.core.publisher.Mono;

public interface TransactionalOperatorGateway {
    <T> Mono<T> execute(Mono<T> action);
    <T> Mono<T> execute(Mono<T> action, boolean readOnly);
}
