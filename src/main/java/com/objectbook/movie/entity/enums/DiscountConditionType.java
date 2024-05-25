package com.objectbook.movie.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiscountConditionType {
    SEQUENCE("seq"),
    PERIOD("period");

    private final String condition;

    @JsonValue
    public String getCondition() {
        return condition;
    }
}
