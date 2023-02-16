package io.github.guilhermeabroncari.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTOInfo {
    private String productDescription;
    private BigDecimal unitaryValue;
    private BigDecimal amount;
}
