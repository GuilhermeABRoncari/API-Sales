package io.github.guilhermeabroncari.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "{Name.field.is.required}")
    private String name;
    @Column(name = "CPF", length = 11)
    @NotEmpty(message = "{CPF.field.is.required}")
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
