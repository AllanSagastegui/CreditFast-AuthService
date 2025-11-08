package pe.ask.creditfast.security.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.token.Token;
import pe.ask.creditfast.model.token.gateways.TokenProviderGateway;
import pe.ask.creditfast.model.user.User;
import pe.ask.creditfast.security.provider.JwtProvider;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TokenProviderAdapter implements TokenProviderGateway {

    private final JwtProvider jwtProvider;


    @Override
    public Mono<Token> generate(User user) {
        return jwtProvider.generateToken(user);
    }
}
