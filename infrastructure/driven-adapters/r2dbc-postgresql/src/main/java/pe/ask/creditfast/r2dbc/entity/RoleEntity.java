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
@Table("tbl_role")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @Column("role_id")
    private UUID id;

    @Column("role_name")
    private String name;

    @Column("role_description")
    private String description;
}
