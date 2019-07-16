package com.example.cinema.ticketprice.domain.fact;

import lombok.Data;

@Data
public class Price {
    private int basicPrice;
    private int discount;
    private int additionalCharge;
    private int totalPrice;
}
