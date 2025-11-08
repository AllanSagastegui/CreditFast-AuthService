package pe.ask.creditfast.model.token.gateways;

import pe.ask.creditfast.model.token.Token;
import pe.ask.creditfast.model.user.User;
import reactor.core.publisher.Mono;

public interface TokenProviderGateway {
    Mono<Token> generate(User user);
}
