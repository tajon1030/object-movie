package com.objectbook.movie.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConditionType {
    ORDER_CONDITION("order"),
    DURATION_CONDITION("duration");

    private final String condition;

    @JsonValue
    public String getCondition() {
        return condition;
    }
}
