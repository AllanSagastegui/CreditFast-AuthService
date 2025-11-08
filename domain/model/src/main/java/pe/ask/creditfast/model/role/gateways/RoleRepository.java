package pe.ask.creditfast.model.role.gateways;

import pe.ask.creditfast.model.role.Role;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RoleRepository {
    Mono<Role> findById(UUID id);
    Mono<Role> findByName(String name);
}
