package com.example.cinema.ticketprice.domain.fact;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StudentType {
    PrimarySchoolStudent("小学生"),
    JuniorHighSchoolStudent("中学生"),
    HighSchoolStudent("高校生"),
    CollageStudent("大学生"),
    Non("学生でない");

    private String label;
    StudentType(String label) {
        this.label = label;
    }

    public boolean is(String lable) {
        return Arrays.stream(values())
                .anyMatch(type -> type.label.equals(lable));
    }
}
