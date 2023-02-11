package io.github.guilhermeabroncari.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    private Long id;
    private String name;
    public Client(String name){
        this.name = name;
    }

}
