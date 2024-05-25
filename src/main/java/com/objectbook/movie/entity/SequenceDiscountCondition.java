package com.objectbook.movie.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@DiscriminatorValue("seq")
public class SequenceDiscountCondition extends DiscountCondition {

    private Long screenSeq;

    @Override
    public boolean isSatisfiedBy(Screen screen) {
        return screen.isSequence(screenSeq);
    }
}
