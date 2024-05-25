package com.objectbook.movie.entity;

import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@DiscriminatorValue("amount")
@Entity
public class AmountDiscountPolicy extends DiscountPolicy {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "discount_amount"))
    private Money discountAmount;

    @Override
    protected Money getDiscountAmount(Screen screen) {
        return discountAmount;
    }
}
