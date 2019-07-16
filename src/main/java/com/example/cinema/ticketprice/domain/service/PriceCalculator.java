package com.example.cinema.ticketprice.domain.service;

import org.springframework.stereotype.Service;

import com.example.cinema.ticketprice.domain.fact.Price;
import com.example.cinema.ticketprice.domain.fact.Ticket;

@Service
public interface PriceCalculator {
    public Price calculate(Ticket ticket);
}
