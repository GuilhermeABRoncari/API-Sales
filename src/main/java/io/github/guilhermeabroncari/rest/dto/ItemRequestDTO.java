package io.github.guilhermeabroncari.rest.dto;

import lombok.*;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    private Long product_id;
    private String description;
    private BigDecimal amount;
}
