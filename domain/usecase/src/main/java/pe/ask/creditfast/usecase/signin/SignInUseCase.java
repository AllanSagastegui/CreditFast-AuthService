package pe.ask.creditfast.usecase.signin;

import lombok.RequiredArgsConstructor;
import pe.ask.creditfast.model.token.Token;
import pe.ask.creditfast.model.token.gateways.TokenProviderGateway;
import pe.ask.creditfast.model.token.gateways.TokenRepository;
import pe.ask.creditfast.model.user.gateways.UserRepository;
import pe.ask.creditfast.model.utils.annotations.Loggable;
import pe.ask.creditfast.model.utils.annotations.TransactionalOperator;
import pe.ask.creditfast.model.utils.gateways.PasswordEncoderGateway;
import pe.ask.creditfast.usecase.utils.exception.InvalidCredentialsException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SignInUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;
    private final TokenRepository tokenRepository;
    private final TokenProviderGateway tokenProviderGateway;

    @Loggable
    @TransactionalOperator
    public Mono<Token> execute(String email, String password) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(InvalidCredentialsException::new))
                .filter(user -> passwordEncoderGateway.matches(password, user.getPassword()))
                .switchIfEmpty(Mono.error(InvalidCredentialsException::new))
                .flatMap(tokenProviderGateway::generate)
                .flatMap(tokenRepository::save);
    }
}
