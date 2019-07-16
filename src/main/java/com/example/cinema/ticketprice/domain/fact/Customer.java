package com.example.cinema.ticketprice.domain.fact;

import lombok.Value;

@Value
public class Customer {
    private Age age;
    private Sex sex;
    private boolean cinemaCitizen;
    private boolean miCard;
}
