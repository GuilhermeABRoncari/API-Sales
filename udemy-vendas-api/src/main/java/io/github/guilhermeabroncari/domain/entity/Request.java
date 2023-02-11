package io.github.guilhermeabroncari.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class Request {
    private Long id;
    private Client client;
    private LocalDate requestDate;
    private BigDecimal amount;

}
