package com.example.cinema.ticketprice.domain.fact;

import java.util.HashSet;
import java.util.Set;

import com.example.cinema.ticketprice.domain.service.PriceCalculator;
import lombok.Getter;

@Getter
public class Ticket {
    private Set<TicketType> ticketTypes;
    private Screen screen;
    private Sheet sheet;
    private Customer customer;
    private Price price;

    public Ticket(Screen screen, Sheet sheet, Customer customer) {
        this.screen = screen;
        this.sheet = sheet;
        this.customer = customer;
        initPrice();
        this.ticketTypes = new HashSet<>();
    }
    private void initPrice() {
        this.price = new Price();
    }

    public int totalPrice() {
        return price.getTotalPrice();
    }

    public boolean contains(String ticketType) {
        return ticketTypes.contains(TicketType.fromLabel(ticketType));
    }

    public void calcurate(PriceCalculator calculator) {
        this.price = calculator.calculate(this);
    }
}
