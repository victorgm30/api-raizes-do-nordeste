package com.raizesdonordeste.raizes_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderDTO {

    private Long customerId;

    private List<OrderItemDTO> items;

}
