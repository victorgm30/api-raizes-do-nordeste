package com.raizesdonordeste.raizes_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {

    private String name;
    private String description;
    private Double price;
    private Boolean active;

    private Long categoryId;
    private Long unitId;

}
