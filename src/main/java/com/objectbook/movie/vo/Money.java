package com.objectbook.movie.vo;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal value) {
    public static final Money ZERO = Money.of(0L);

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public Money times(double rate) {
        return new Money(this.value.multiply(BigDecimal.valueOf(rate)));
    }

    public Money minus(Money money) {
        return new Money(this.value.subtract(money.value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

}
