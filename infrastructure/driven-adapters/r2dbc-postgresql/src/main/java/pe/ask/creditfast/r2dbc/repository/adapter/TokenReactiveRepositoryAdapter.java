package pe.ask.creditfast.r2dbc.repository.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.creditfast.model.token.Token;
import pe.ask.creditfast.model.token.gateways.TokenRepository;
import pe.ask.creditfast.r2dbc.entity.TokenEntity;
import pe.ask.creditfast.r2dbc.helper.ReactiveAdapterOperations;
import pe.ask.creditfast.r2dbc.repository.ITokenReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class TokenReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Token,
        TokenEntity,
        UUID,
        ITokenReactiveRepository
        > implements TokenRepository {

    public TokenReactiveRepositoryAdapter(ITokenReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Token.class));
    }

    @Override
    public Mono<Token> save(Token token) {
        return repository.save(toData(token))
                .map(this::toEntity);
    }
}
