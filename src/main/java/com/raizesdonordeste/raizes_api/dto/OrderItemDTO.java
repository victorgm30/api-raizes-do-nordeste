package com.raizesdonordeste.raizes_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private Long productId;
    private Integer quantity;

}
