package com.objectbook.movie.entity;


import com.objectbook.movie.entity.enums.ConditionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DiscountCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private ConditionType conditionType;

    private Integer dateOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long screenSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_policy_id")
    private DiscountPolicy discountPolicy;
}
