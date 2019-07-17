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
        def customer = new Customer(new Age(toDate(birthDay), toDate(screenDate)), sex, studentType, cinemaCitizen, miCard, disability)
        def ticket = new Ticket(screen, sheet, customer)


        expect:
        DroolsPriceCalculator calculator = new DroolsPriceCalculator()
        ticket.calcurate(calculator)
        ticket.getPrice().totalPrice == result

        where:
        title                                      | screenDate    | startTime | birthDay     | sex      | studentType     | sheetType        | cinemaCitizen | miCard | disability || result
        "シネマシティズン、平日、レイト無、映画の日無"   | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、平日、レイト有、映画の日無"   | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、休日、レイト無、映画の日無"   | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | true          | false  | false      || 1300
        "シネマシティズン、休日、レイト有、映画の日無"   | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、映画の日有、平日"            | "2019-07-01"  | "14:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、映画の日有、休日"            | "2019-06-01"  | "14:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シネマシティズン、シニア"                     | "2019-06-01"  | "14:00"   | "1940-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "一般、平日、レイト無、映画の日無"              | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1800
        "一般、平日、レイト有、映画の日無"              | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1300
        "一般、休日、レイト無、映画の日無"              | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1800
        "一般、休日、レイト有、映画の日無"              | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1300
        "一般、映画の日有"                            | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シニア、平日、レイト無、映画の日無"             | "2019-07-08"  | "15:00"   | "1930-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シニア、平日、レイト有、映画の日無"             | "2019-07-08"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シニア、休日、レイト無、映画の日無"             | "2019-07-07"  | "15:00"   | "1930-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シニア、休日、レイト有、映画の日無"             | "2019-07-07"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
        "シニア、映画の日有"                           | "2019-07-01"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non | SheetType.Normal | false         | false  | false      || 1100
    }

    static LocalDate toDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    static LocalTime toDateTime(final String dateTime) {
        return LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
