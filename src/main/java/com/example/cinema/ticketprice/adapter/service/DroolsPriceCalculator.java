package com.example.cinema.ticketprice.adapter.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.example.cinema.ticketprice.domain.fact.Price;
import com.example.cinema.ticketprice.domain.fact.Ticket;
import com.example.cinema.ticketprice.domain.service.PriceCalculator;

public class DroolsPriceCalculator implements PriceCalculator {
    @Override
    public Price calculate(Ticket ticket) {
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.getKieBase().newKieSession();
        KieServices.Factory.get().getLoggers().newConsoleLogger(kieSession);
        kieSession.insert(ticket);
        kieSession.fireAllRules();
        return ticket.getPrice();
    }
}
