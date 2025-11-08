package pe.ask.creditfast.r2dbc.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.creditfast.r2dbc.entity.RoleEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IRoleReactiveRepository extends ReactiveCrudRepository<RoleEntity, UUID>, ReactiveQueryByExampleExecutor<RoleEntity> {
    Mono<RoleEntity> findByName(String name);
}
