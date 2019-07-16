package com.example.cinema.ticketprice.domain.fact;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import com.example.cinema.ticketprice.adapter.service.DroolsPriceCalculator;

public class TicketTest {

    @Test
    public void test() {
        // Given
        Cinema cinema = new Cinema("天気の子");
        LocalDate screenDate = LocalDate.of(2019,07,17);
        Screen screen = new Screen(cinema, screenDate, LocalTime.of(15, 0), 90);
        Sheet sheet = new Sheet(SheetType.Normal);
        LocalDate birthDay = LocalDate.of(1982, 11, 11);
        Customer customer = new Customer(new Age(birthDay, screenDate), Sex.Male, false, false);
        Ticket ticket = new Ticket(screen, sheet, customer);
        // When
        DroolsPriceCalculator calculator = new DroolsPriceCalculator();
        ticket.calcurate(calculator);
        // Then
        assertThat(ticket.totalPrice(), is(1800));
    }
}