package io.github.guilhermeabroncari.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTOInfo {
    private Long code;
    private String cpf;
    private String clientName;
    private BigDecimal totalPrice;
    private String requestDate;
    private String status;
    private List<ItemRequestDTOInfo> items;
}
