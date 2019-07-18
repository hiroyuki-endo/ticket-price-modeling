package com.example.cinema.ticketprice.domain.fact;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Cinema {
    private String title;
    private Set<Effective> effectives;

    public Cinema(String title, Effective... effectives) {
        this.title = title;
        this.effectives = new HashSet<>(Arrays.asList(effectives));
    }

    public boolean contains(String effective) {
        return effectives.contains(Effective.fromLabel(effective));
    }

    public boolean notContains(String effective) {
        return !effectives.contains(Effective.fromLabel(effective));
    }
}
