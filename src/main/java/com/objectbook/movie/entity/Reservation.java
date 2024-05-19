package com.objectbook.movie.entity;

import com.objectbook.movie.vo.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 임시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "actual_price"))
    private Money actualPrice;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "regular_price"))
    private Money regularPrice;

    private Integer count;

    public Reservation makeReservation(Long userId, Screen screen, Integer count){
        // 할인가
        Money actualTotalPrice = screen.getActualPrice()
                .times(count);

        // 정가
        Money regularTotalPrice = screen.getMovie()
                .getPrice()
                .times(count);

        return Reservation.builder()
                .userId(userId)
                .screen(screen)
                .actualPrice(actualTotalPrice)
                .regularPrice(regularTotalPrice)
                .count(count)
                .build();
    }
}
