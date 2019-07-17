package com.example.cinema.ticketprice.domain.fact;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import lombok.Value;

@Value
public class Screen {
    private Cinema cinema;
    private LocalDate date;
    private LocalTime startTime;
    private int minutes;
    private static final List<DayOfWeek> holidays = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public boolean isHoliDay() {
        return holidays.contains(date.getDayOfWeek());
    }
    public boolean isWeekDay() {
        return !isHoliDay();
    }
}
