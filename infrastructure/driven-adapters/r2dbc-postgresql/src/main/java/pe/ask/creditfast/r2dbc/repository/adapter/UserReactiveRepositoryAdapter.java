package pe.ask.creditfast.r2dbc.repository.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.creditfast.model.user.User;
import pe.ask.creditfast.model.user.gateways.UserRepository;
import pe.ask.creditfast.r2dbc.entity.UserEntity;
import pe.ask.creditfast.r2dbc.helper.ReactiveAdapterOperations;
import pe.ask.creditfast.r2dbc.repository.IUserReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        UUID,
        IUserReactiveRepository
        > implements UserRepository {

    public UserReactiveRepositoryAdapter(IUserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }


    @Override
    public Mono<User> signUp(User user) {
        return repository.save(toData(user))
                .map(this::toEntity);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return this.repository.findByEmail(email)
                .map(this::toEntity);
    }

    @Override
    public Mono<User> findByDni(String dni) {
        return repository.findByDni(dni)
                .map(this::toEntity);
    }
}
