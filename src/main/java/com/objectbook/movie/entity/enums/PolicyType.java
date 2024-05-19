package com.objectbook.movie.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PolicyType {
    DISCOUNT_AMOUNT("amount"),
    DISCOUNT_PERCENTAGE("percentage");

    private final String policy;

    @JsonValue
    public String getPolicy() {
        return policy;
    }
}
