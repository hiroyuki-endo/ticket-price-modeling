package com.example.cinema.ticketprice.domain.fact

import com.example.cinema.ticketprice.adapter.service.DroolsPriceCalculator
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketTest extends spock.lang.Specification {

    @Unroll
    def "#titleで#resultが返る()"() {
        setup:
        def cinema = new Cinema("天気の子")
        def screen = new Screen(cinema, toDate(screenDate), toDateTime(startTime), 90)
        def sheet = new Sheet(sheetType)
        def customer = new Customer(new Age(toDate(birthDay), toDate(screenDate)), sex, cinemaCitizen, miCard)
        def ticket = new Ticket(screen, sheet, customer)


        expect:
        DroolsPriceCalculator calculator = new DroolsPriceCalculator()
        ticket.calcurate(calculator)
        ticket.getPrice().totalPrice == result

        where:
        title                     | screenDate    | startTime | birthDay     | sex      | sheetType        | cinemaCitizen | miCard || result
        "平日、レイト無、映画の日無"  | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | SheetType.Normal | false         | false  || 1800
        "平日、レイト有、映画の日無"  | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | SheetType.Normal | false         | false  || 1300
    }

    static LocalDate toDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    static LocalTime toDateTime(final String dateTime) {
        return LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
