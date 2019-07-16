package com.example.cinema.ticketprice.domain.fact;

public enum Effective {
    ThreeDemension("3D"),
    GokujoBakuonJoei("極上爆音上映");

    private String label;
    Effective(String label) {
        this.label = label;
    }
}
