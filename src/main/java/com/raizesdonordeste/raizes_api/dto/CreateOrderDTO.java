package com.raizesdonordeste.raizes_api.dto;

import com.raizesdonordeste.raizes_api.enums.OrderChannel;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderDTO {

    private Long customerId;

    @NotNull(message = "The order channel must be provided.")
    private OrderChannel channel;

    private List<OrderItemDTO> items;

}
