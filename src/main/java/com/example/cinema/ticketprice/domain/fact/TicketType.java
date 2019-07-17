package com.example.cinema.ticketprice.domain.fact;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum TicketType {
    CinemaCitizen("シネマシティズン"),
    SeniorCinemaCitizen("シネマシティズン（60才以上）"),
    General("一般"),
    Senior("シニア（70才以上）"),
    CollageStudent("学生（大・専）"),
    SchoolStudent("中・高校生"),
    Child("幼児（3才以上）・小学生"),
    HigherOfDisability("障がい者（学生以上）"),
    LowerOfDisability("障がい者（高校以下）"),
    MarriedCouple("夫婦50割引"),
    MiCard("エムアイカード"),
    ParkingLot("駐車場パーク80割引"),
    Non("未設定");

    private String label;
    TicketType(String label) {
        this.label = label;
    }

    public static TicketType fromLabel(String label) {
        return Arrays.stream(values())
                .filter(var -> label.equals(var.label))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
