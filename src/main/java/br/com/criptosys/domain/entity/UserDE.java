package br.com.criptosys.domain.entity;

import br.com.criptosys.shared.domain.BaseLogDE;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "USERS")
@AttributeOverrides({
        @AttributeOverride(name = "dateCreate", column = @Column(name = "DATE_CREATE", columnDefinition = "Timestamp")),
        @AttributeOverride(name = "createUser", column = @Column(name = "CREATE_USER", length = 20)),
        @AttributeOverride(name = "processCreate", column = @Column(name = "PROCESS_CREATE", length = 70)),
        @AttributeOverride(name = "dateUpdate", column = @Column(name = "DATE_UPDATE", columnDefinition = "Timestamp")),
        @AttributeOverride(name = "updateUser", column = @Column(name = "UPDATE_USER", length = 20)),
        @AttributeOverride(name = "processUpdate", column = @Column(name = "PROCESS_UPDATE", length = 70)),
})
@Getter
@Setter
@Builder
public class UserDE extends BaseLogDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", length = 20, nullable = false)
    private BigInteger userId;

    @Column(name = "NAME", length = 40)
    private String name;

    @Column(name = "EMAIL", length = 20)
    private String email;

    @Column(name = "USERNAME", length = 20)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private Boolean active;

}
