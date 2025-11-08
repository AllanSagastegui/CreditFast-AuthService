package pe.ask.creditfast.r2dbc.repository.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.creditfast.model.role.Role;
import pe.ask.creditfast.model.role.gateways.RoleRepository;
import pe.ask.creditfast.r2dbc.entity.RoleEntity;
import pe.ask.creditfast.r2dbc.helper.ReactiveAdapterOperations;
import pe.ask.creditfast.r2dbc.repository.IRoleReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RoleReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Role,
        RoleEntity,
        UUID,
        IRoleReactiveRepository
        > implements RoleRepository {
    
    public RoleReactiveRepositoryAdapter(IRoleReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Role.class));
    }
    
    
    @Override
    public Mono<Role> findById(UUID id) {
        return repository.findById(id)
                .map(this::toEntity);
    }

    @Override
    public Mono<Role> findByName(String name) {
        return repository.findByName(name)
                .map(this::toEntity);
    }
}
