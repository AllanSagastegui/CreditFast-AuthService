package pe.ask.creditfast.r2dbc.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.creditfast.r2dbc.entity.UserEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IUserReactiveRepository extends ReactiveCrudRepository<UserEntity, UUID>, ReactiveQueryByExampleExecutor<UserEntity> {
    Mono<UserEntity> findByEmail(String email);
    Mono<UserEntity> findByDni(String dni);
}
