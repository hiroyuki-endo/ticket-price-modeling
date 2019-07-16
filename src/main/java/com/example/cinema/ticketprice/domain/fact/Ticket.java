package com.example.cinema.ticketprice.domain.fact;

import com.example.cinema.ticketprice.domain.service.PriceCalculator;
import lombok.Getter;

@Getter
public class Ticket {
    private Screen screen;
    private Sheet sheet;
    private Customer customer;
    private Price price;

    public Ticket(Screen screen, Sheet sheet, Customer customer) {
        this.screen = screen;
        this.sheet = sheet;
        this.customer = customer;
        initPrice();
    }
    private void initPrice() {
        this.price = new Price();
    }

    public int totalPrice() {
        return price.getTotalPrice();
    }

    public void calcurate(PriceCalculator calculator) {
        this.price = calculator.calculate(this);
    }
}
