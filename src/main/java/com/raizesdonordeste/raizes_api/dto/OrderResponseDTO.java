package com.raizesdonordeste.raizes_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;
    private String status;
    private Double total;

}
