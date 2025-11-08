package pe.ask.creditfast.security.provider;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.role.Role;
import pe.ask.creditfast.model.role.gateways.RoleRepository;
import pe.ask.creditfast.model.token.Token;
import pe.ask.creditfast.model.user.User;
import pe.ask.creditfast.security.utils.TokenExpirationTime;
import reactor.core.publisher.Mono;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final PrivateKey privateKey;
    private final RoleRepository roleRepository;

    public Mono<Token> generateToken(User user){
        return roleRepository.findById(user.getIdRole())
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(role -> Mono.zip(
                        generateAccessToken(user.getId(), role),
                        generateRefreshToken(user.getId()),
                        (access, refresh) -> Token.builder()
                                .accessToken(access)
                                .refreshToken(refresh)
                                .tokenType("Bearer")
                                .accessExpiresIn(TokenExpirationTime.ACCESS.getSeconds())
                                .refreshExpiresIn(TokenExpirationTime.REFRESH.getSeconds())
                                .userId(user.getId())
                                .build()
                ));
    }

    private Mono<String> generateAccessToken(UUID idUser, Role role) {
        return Mono.fromSupplier(() -> Jwts.builder()
                        .subject(idUser.toString())
                        .issuedAt(new Date())
                        .expiration(Date.from(Instant.now().plusSeconds(TokenExpirationTime.ACCESS.getSeconds())))
                        .claims(
                                Map.of(
                                        "type", "access",
                                        "role", role.getName(),
                                        "userId", idUser.toString()
                                )
                        )
                        .signWith(privateKey, Jwts.SIG.RS256)
                        .compact()
        );
    }

    private Mono<String> generateRefreshToken(UUID userId) {
        return Mono.fromSupplier(() -> Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusSeconds(TokenExpirationTime.REFRESH.getSeconds())))
                .claim("type", "refresh")
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact()
        );
    }
}
