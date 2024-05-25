package com.objectbook.movie.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@DiscriminatorValue("period")
public class PeriodDiscountCondition extends DiscountCondition {

    private DayOfWeek dateOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    @Override
    public boolean isSatisfiedBy(Screen screen) {
        return screen.getScreeningDatetime().toLocalTime().isBefore(getEndTime())
                && screen.getScreeningDatetime().toLocalTime().isAfter(getStartTime())
                && screen.getScreeningDatetime().getDayOfWeek().equals(getDateOfWeek());
    }
}
