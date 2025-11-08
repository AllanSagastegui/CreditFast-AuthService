package pe.ask.creditfast.r2dbc.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.creditfast.r2dbc.entity.TokenEntity;

import java.util.UUID;

public interface ITokenReactiveRepository extends ReactiveCrudRepository<TokenEntity, UUID>, ReactiveQueryByExampleExecutor<TokenEntity> {
}
