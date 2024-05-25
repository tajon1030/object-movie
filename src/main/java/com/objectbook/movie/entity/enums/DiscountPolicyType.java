package com.objectbook.movie.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiscountPolicyType {
    NONE("none"),
    AMOUNT("amount"),
    PERCENTAGE("percentage");

    private final String policy;

    @JsonValue
    public String getPolicy() {
        return policy;
    }
}
