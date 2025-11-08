package pe.ask.creditfast.usecase.signup;

import lombok.RequiredArgsConstructor;
import pe.ask.creditfast.model.role.gateways.RoleRepository;
import pe.ask.creditfast.model.user.User;
import pe.ask.creditfast.model.user.gateways.UserRepository;
import pe.ask.creditfast.model.utils.annotations.Loggable;
import pe.ask.creditfast.model.utils.annotations.TransactionalOperator;
import pe.ask.creditfast.model.utils.gateways.PasswordEncoderGateway;
import pe.ask.creditfast.usecase.utils.Roles;
import pe.ask.creditfast.usecase.utils.exception.RoleNotFoundException;
import pe.ask.creditfast.usecase.utils.exception.UserAlreadyExistsException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;

    @Loggable
    @TransactionalOperator
    public Mono<User> execute(User user) {
        return userRepository.findByEmail(user.getEmail())
                .flatMap(existing -> Mono.<User>error(UserAlreadyExistsException::new))
                .switchIfEmpty(
                        userRepository.findByDni(user.getDni())
                                .flatMap(existing -> Mono.<User>error(UserAlreadyExistsException::new))
                                .switchIfEmpty(
                                        roleRepository.findByName(Roles.CLIENT.name())
                                                .switchIfEmpty(Mono.error(RoleNotFoundException::new))
                                                .map(role -> user.toBuilder()
                                                        .password(passwordEncoderGateway.hash(user.getPassword()))
                                                        .idRole(role.getId())
                                                        .build()
                                                )
                                                .flatMap(userRepository::signUp)
                                )
                );
    }
}
