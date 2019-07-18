package com.example.cinema.ticketprice.domain.fact

import com.example.cinema.ticketprice.adapter.service.DroolsPriceCalculator
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketTest extends spock.lang.Specification {

    @Unroll
    def "通常の料金バリエーション_#titleで#resultが返る()"() {
        setup:
        def cinema = new Cinema("天気の子")
        def screen = new Screen(cinema, toDate(screenDate), toDateTime(startTime), 90)
        def sheet = new Sheet(sheetType)
        def customer = new Customer(new Age(toDate(birthDay), toDate(screenDate)), sex, studentType, cinemaCitizen, miCard, disability, false)
        def ticket = new Ticket(screen, sheet, customer)

        expect:
        DroolsPriceCalculator calculator = new DroolsPriceCalculator()

        ticket.calcurate(calculator)
        ticket.getPrice().totalPrice == result

        where:
        title                                      | screenDate    | startTime | birthDay     | sex      | studentType                           | sheetType        | cinemaCitizen | miCard | disability || result
        "シネマシティズン、平日、レイト無、映画の日無"   | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、平日、レイト有、映画の日無"   | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、休日、レイト無、映画の日無"   | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | true          | false  | false      || 1300
        "シネマシティズン、休日、レイト有、映画の日無"   | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、映画の日有、平日"            | "2019-07-01"  | "14:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | true          | false  | false      || 1000
        "シネマシティズン、映画の日有、休日"            | "2019-06-01"  | "14:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シネマシティズン、シニア"                     | "2019-06-01"  | "14:00"   | "1940-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "一般、平日、レイト無、映画の日無"              | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1800
        "一般、平日、レイト有、映画の日無"              | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1300
        "一般、休日、レイト無、映画の日無"              | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1800
        "一般、休日、レイト有、映画の日無"              | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1300
        "一般、映画の日有"                            | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シニア、平日、レイト無、映画の日無"             | "2019-07-08"  | "15:00"   | "1930-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シニア、平日、レイト有、映画の日無"             | "2019-07-08"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シニア、休日、レイト無、映画の日無"             | "2019-07-07"  | "15:00"   | "1930-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シニア、休日、レイト有、映画の日無"             | "2019-07-07"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "シニア、映画の日有"                           | "2019-07-01"  | "20:00"   | "1930-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal | false         | false  | false      || 1100
        "学生（大・専）、平日、レイト無、映画の日無"      | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.CollageStudent            | SheetType.Normal | false         | false  | false      || 1500
        "学生（大・専）、平日、レイト有、映画の日無"      | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.CollageStudent            | SheetType.Normal | false         | false  | false      || 1300
        "学生（大・専）、休日、レイト無、映画の日無"      | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.CollageStudent            | SheetType.Normal | false         | false  | false      || 1500
        "学生（大・専）、休日、レイト有、映画の日無"      | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.CollageStudent            | SheetType.Normal | false         | false  | false      || 1300
        "学生（大・専）、映画の日有"                    | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.CollageStudent            | SheetType.Normal | false         | false  | false      || 1100
        "中・高校生、平日、レイト無、映画の日無"         | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.HighSchoolStudent          | SheetType.Normal | false         | false  | false      || 1000
        "中・高校生、平日、レイト有、映画の日無"         | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.HighSchoolStudent          | SheetType.Normal | false         | false  | false      || 1000
        "中・高校生、休日、レイト無、映画の日無"         | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.HighSchoolStudent          | SheetType.Normal | false         | false  | false      || 1000
        "中・高校生、休日、レイト有、映画の日無"         | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.HighSchoolStudent          | SheetType.Normal | false         | false  | false      || 1000
        "中・高校生、映画の日有"                       | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.HighSchoolStudent          | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、平日、レイト無、映画の日無" | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.JuniorHighSchoolStudent | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、平日、レイト有、映画の日無" | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.JuniorHighSchoolStudent | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、休日、レイト無、映画の日無" | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.JuniorHighSchoolStudent | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、休日、レイト有、映画の日無" | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.JuniorHighSchoolStudent | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、映画の日有"               | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.JuniorHighSchoolStudent | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、平日、レイト無、映画の日無" | "2019-07-08"  | "15:00"   | "2014-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、平日、レイト有、映画の日無" | "2019-07-08"  | "20:00"   | "2014-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、休日、レイト無、映画の日無" | "2019-07-07"  | "15:00"   | "2014-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、休日、レイト有、映画の日無" | "2019-07-07"  | "20:00"   | "2014-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | false      || 1000
        "幼児（3才以上）・小学生、映画の日有"               | "2019-07-01"  | "20:00"   | "2014-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | false      || 1000
        "障がい者（学生以上）、平日、レイト無、映画の日無"    | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 1000
        "障がい者（学生以上）、平日、レイト有、映画の日無"    | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 1000
        "障がい者（学生以上）、休日、レイト無、映画の日無"    | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 1000
        "障がい者（学生以上）、休日、レイト有、映画の日無"    | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 1000
        "障がい者（学生以上）、映画の日有"                  | "2019-07-01"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 1000
        "障がい者（高校以下）、平日、レイト無、映画の日無"    | "2019-07-08"  | "15:00"   | "2005-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 900
        "障がい者（高校以下）、平日、レイト有、映画の日無"    | "2019-07-08"  | "20:00"   | "2005-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 900
        "障がい者（高校以下）、休日、レイト無、映画の日無"    | "2019-07-07"  | "15:00"   | "2005-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 900
        "障がい者（高校以下）、休日、レイト有、映画の日無"    | "2019-07-07"  | "20:00"   | "2005-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 900
        "障がい者（高校以下）、映画の日有"                  | "2019-07-01"  | "20:00"   | "2005-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | false  | true       || 900
        "エムアイカード、平日、レイト無、映画の日無"         | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | true   | false      || 1600
        "エムアイカード、平日、レイト有、映画の日無"         | "2019-07-08"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | true   | false      || 1300
        "エムアイカード、休日、レイト無、映画の日無"         | "2019-07-07"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | true   | false      || 1600
        "エムアイカード、休日、レイト有、映画の日無"         | "2019-07-07"  | "20:00"   | "1990-11-11" | Sex.Male | StudentType.Non                     | SheetType.Normal | false         | true   | false      || 1300
    }

    @Unroll
    def "3D_#titleで#totalが返る()"() {
        setup:
        def cinema = new Cinema("天気の子", Effective.ThreeDemension)
        def screen = new Screen(cinema, toDate(screenDate), toDateTime(startTime), 90)
        def sheet = new Sheet(sheetType)
        def customer = new Customer(new Age(toDate(birthDay), toDate(screenDate)), sex, studentType, false, false, false, has3DGlasses)
        def ticket = new Ticket(screen, sheet, customer)

        expect:
        DroolsPriceCalculator calculator = new DroolsPriceCalculator()

        ticket.calcurate(calculator)
        ticket.getPrice().additionalCharge == additionalCharge
        ticket.getPrice().totalPrice == total

        where:
        title    | screenDate    | startTime | birthDay     | sex      | studentType                           | sheetType         | has3DGlasses || total | additionalCharge
        "3D"     | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal  | false        || 2200  | 400
        "3D持参"  | "2019-07-08"  | "15:00"   | "1990-11-11" | Sex.Male | StudentType.Non                       | SheetType.Normal  | true        || 2100   | 300
    }

    static LocalDate toDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    static LocalTime toDateTime(final String dateTime) {
        return LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HH:mm"))
    }
}
