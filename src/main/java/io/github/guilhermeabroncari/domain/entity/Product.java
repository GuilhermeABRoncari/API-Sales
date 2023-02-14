package io.github.guilhermeabroncari.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    private String description;
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private Set<ItemRequest> itemRequestSet;
}
