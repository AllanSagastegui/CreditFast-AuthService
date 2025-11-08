package pe.ask.creditfast.model.token.gateways;

import pe.ask.creditfast.model.token.Token;
import reactor.core.publisher.Mono;

public interface TokenRepository {
    Mono<Token> save(Token token);
}
