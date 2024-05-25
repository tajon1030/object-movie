package com.objectbook.movie.entity;

import com.objectbook.movie.converter.DurationConverter;
import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Convert(converter = DurationConverter.class)
    private Duration runningTime;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "fee"))
    private Money fee;

    @OneToOne(mappedBy = "movie")
    private DiscountPolicy discountPolicy;

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public void setRunningTime(int hours, int minutes) {
        this.runningTime = Duration.ofHours(hours).plusMinutes(minutes);
    }

    public Money calculateDiscountedMovieFee(Screen screen) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screen));
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
