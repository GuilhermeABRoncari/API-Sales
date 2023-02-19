package io.github.guilhermeabroncari.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{Name.field.is.required}")
    private String name;
    @Column(name = "CPF", length = 11)
    @NotBlank(message = "{CPF.field.is.required}")
    @CPF(message = "Provide a valid CPF.")
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Request> requestList;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requestSet=" + requestList +
                '}';
    }
}
