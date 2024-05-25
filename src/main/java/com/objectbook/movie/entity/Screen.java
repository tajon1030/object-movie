package com.objectbook.movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    public boolean isSequence(long seq) {
        return this.seq == seq;
    }

    public Reservation makeReservation(Long userId, int count) {
        return Reservation.builder()
                .userId(userId)
                .screen(this)
                .actualPrice(movie.calculateDiscountedMovieFee(this).times(count))
                .count(count)
                .build();
    }
}
