package io.github.guilhermeabroncari.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long client_id;
    private BigDecimal totalPrice;
    private List<ItemRequestDTO> items;
}
