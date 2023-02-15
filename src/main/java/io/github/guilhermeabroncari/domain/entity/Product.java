package io.github.guilhermeabroncari.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    private String description;
    @Column(name = "PRICE", precision = 20, scale = 2)
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ItemRequest> itemRequestSet;
}
