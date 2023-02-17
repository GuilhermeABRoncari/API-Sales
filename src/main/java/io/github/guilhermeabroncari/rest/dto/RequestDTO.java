package io.github.guilhermeabroncari.rest.dto;

import io.github.guilhermeabroncari.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @NotNull(message = "{Enter.costumer.code}")
    private Long client_id;
    @NotNull(message = "{Total.price.field.is.mandatory}")
    private BigDecimal totalPrice;
    @NotEmptyList(message = "{Order.cannot.be.placed.without.items}")
    private List<ItemRequestDTO> items;
}
