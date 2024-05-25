package com.objectbook.movie.entity;

import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorColumn(name = "policy_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class DiscountPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "discountPolicy")
    private List<DiscountCondition> discountConditions;

    public Money calculateDiscountAmount(Screen screen) {
        for (DiscountCondition each : discountConditions) {
            if (each.isSatisfiedBy(screen)) {
                return getDiscountAmount(screen);
            }
        }

        return Money.ZERO;
    }

    public Money getMovieFee(){
        return movie.getFee();
    }

    abstract protected Money getDiscountAmount(Screen screen);

}
