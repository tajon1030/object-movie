package com.objectbook.movie.entity;

import com.objectbook.movie.entity.enums.PolicyType;
import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class DiscountPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Enumerated
    private PolicyType policyType;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "discount_amount"))
    private Money discountAmount;

    private Double discountRate;

    @OneToMany(mappedBy = "discountPolicy")
    private List<DiscountCondition> discountConditions;

}
