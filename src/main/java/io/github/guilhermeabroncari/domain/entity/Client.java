package io.github.guilhermeabroncari.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
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
