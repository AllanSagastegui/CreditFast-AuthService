package pe.ask.creditfast.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table("tbl_token")
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    @Id
    @Column("token_id")
    private UUID id;

    @Column("access_token")
    private String accessToken;

    @Column("refresh_token")
    private String refreshToken;

    @Column("token_type")
    private String tokenType;

    @Column("access_token_expiration")
    private long accessExpiresIn;

    @Column("refresh_token_expiration")
    private long refreshExpiresIn;

    @Column("user_id")
    private UUID userId;
}
