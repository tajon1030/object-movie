package com.objectbook.movie.entity;

import com.objectbook.movie.vo.Money;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue("none")
@Entity
@SuperBuilder
public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screen screen) {
        return Money.ZERO;
    }

}
