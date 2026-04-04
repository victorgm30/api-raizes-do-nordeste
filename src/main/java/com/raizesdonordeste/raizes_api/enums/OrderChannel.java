package com.raizesdonordeste.raizes_api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public enum OrderChannel {

    TOTEM,
    BALCAO,
    APP;

    @JsonCreator
    public static OrderChannel fromString(String value) {
        return OrderChannel.valueOf(value.toUpperCase());
    }
    
}
