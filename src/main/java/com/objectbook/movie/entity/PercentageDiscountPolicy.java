package com.objectbook.movie.entity;

import com.objectbook.movie.vo.Money;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue("percentage")
@Entity
@SuperBuilder
public class PercentageDiscountPolicy extends DiscountPolicy {

    private Double discountPercentage;

    @Override
    protected Money getDiscountAmount(Screen screen) {
        return getMovieFee().times(discountPercentage);
    }
}
