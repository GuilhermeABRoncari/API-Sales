package io.github.guilhermeabroncari.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private Long id;
    private Request request;
    private Product product;
    private int amount;
}
