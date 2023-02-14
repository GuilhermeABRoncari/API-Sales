package io.github.guilhermeabroncari.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ITEM_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "REQUEST_ID")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @Column
    private int amount;
}
