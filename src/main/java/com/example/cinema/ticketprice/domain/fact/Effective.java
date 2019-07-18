package com.example.cinema.ticketprice.domain.fact;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Effective {
    ThreeDemension("3D"),
    GokujoBakuonJoei("極上爆音上映");

    private String label;
    Effective(String label) {
        this.label = label;
    }

    public static Effective fromLabel(String label) {
        return Arrays.stream(values())
                .filter(var -> label.equals(var.label))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
