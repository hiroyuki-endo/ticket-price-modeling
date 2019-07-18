package com.example.cinema.ticketprice.domain.fact;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Price {
    private int basicPrice;
    private int discount;
    private int additionalCharge;
    private List<String> logs = new ArrayList<>();

    public void addAdditionalCharge(int charge) {
        additionalCharge = additionalCharge + charge;
    }
    public void addLogs(String message) {
        logs.add(message);
    }
    public boolean notContains(String message) {
        return !logs.contains(message);
    }

    public int getTotalPrice() {
        return basicPrice - discount + additionalCharge;
    }
}
