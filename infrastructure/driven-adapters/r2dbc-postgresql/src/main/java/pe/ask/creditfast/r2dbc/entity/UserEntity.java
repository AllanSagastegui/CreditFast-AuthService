package pe.ask.creditfast.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Table("tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column("user_id")
    private UUID id;

    @Column("user_name")
    private String name;

    @Column("user_surname")
    private String surname;

    @Column("user_dni")
    private String dni;

    @Column("user_email")
    private String email;

    @Column("user_password")
    private String password;

    @Column("user_birthday")
    private LocalDate birthday;

    @Column("user_address")
    private String address;

    @Column("user_phone")
    private String phone;

    @Column("user_base_salary")
    private BigDecimal baseSalary;

    @Column("id_role")
    private UUID idRole;
}
