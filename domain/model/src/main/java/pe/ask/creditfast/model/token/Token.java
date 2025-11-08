package pe.ask.creditfast.model.token;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Token {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long accessExpiresIn;
    private long refreshExpiresIn;
    private UUID userId;
}
