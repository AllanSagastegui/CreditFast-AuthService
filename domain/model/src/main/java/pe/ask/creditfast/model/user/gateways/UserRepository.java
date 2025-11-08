package pe.ask.creditfast.model.user.gateways;

import pe.ask.creditfast.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> signUp(User user);
    Mono<User> findByEmail(String email);
    Mono<User> findByDni(String dni);
}
