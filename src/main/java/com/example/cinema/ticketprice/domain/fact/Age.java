package com.example.cinema.ticketprice.domain.fact;

import java.time.LocalDate;
import java.time.Period;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Age {
    private int value;
    public Age(LocalDate birthDay, LocalDate bookDate) {
        value = Period.between(birthDay, bookDate).getYears();
    }
}
