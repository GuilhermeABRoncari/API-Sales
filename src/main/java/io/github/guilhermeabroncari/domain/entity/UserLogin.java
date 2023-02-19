package io.github.guilhermeabroncari.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERLOGIN")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty(message = "{Invalid.user.name}")
    private String login;
    @Column
    @NotEmpty(message = "{Invalid.password}")
    private String password;
    @Column
    private boolean admin;
}
