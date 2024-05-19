package com.objectbook.movie.entity;

import com.objectbook.movie.entity.enums.ConditionType;
import com.objectbook.movie.entity.enums.PolicyType;
import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long seq;

    private LocalDateTime screeningDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screen")
    private List<Reservation> reservations;

    public Money getActualPrice(){
        // 1. 예매 정보가 할인 조건중 하나라도 만족하는지 검사
        if(isDiscountConditionMatched()){
            // 2. 만족할 경우 할인 요금 계산
            PolicyType moviePolicyType = movie.getDiscountPolicy().getPolicyType();
            if(PolicyType.DISCOUNT_AMOUNT.equals(moviePolicyType)){
                return movie.getPrice().minus(movie.getDiscountPolicy().getDiscountAmount());
            }else if(PolicyType.DISCOUNT_PERCENTAGE.equals(moviePolicyType)) {
                return movie.getPrice().minus(movie.getPrice()
                        .times(movie.getDiscountPolicy().getDiscountRate()));
            }
        }
        return movie.getPrice();
    }

    public boolean isDiscountConditionMatched(){
        return movie.getDiscountPolicy()
                .getDiscountConditions().stream()
                .anyMatch(condition -> {
                    if(ConditionType.DURATION_CONDITION.equals(condition.getConditionType())){
                        return screeningDatetime.toLocalTime().isBefore(condition.getEndTime())
                                && screeningDatetime.toLocalTime().isAfter(condition.getStartTime())
                                && screeningDatetime.getDayOfWeek().getValue() == condition.getDateOfWeek();
                    }else if (ConditionType.ORDER_CONDITION.equals(condition.getConditionType())){
                        return Objects.equals(seq, condition.getScreenSeq());
                    }
                    return false;
                });
    }
}
