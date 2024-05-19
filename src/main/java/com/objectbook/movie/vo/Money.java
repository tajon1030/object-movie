package com.objectbook.movie.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal value;

    public Money(BigDecimal value){
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Money times(double rate){
        return new Money(this.value.multiply(BigDecimal.valueOf(rate)));
    }

    public Money minus(Money salePrice) {
        BigDecimal result = this.value.subtract(salePrice.value)
                .multiply(BigDecimal.valueOf(100))
                .divide(this.value, RoundingMode.HALF_EVEN);
        return new Money(result);
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
