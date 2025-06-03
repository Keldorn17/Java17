package com.keldorn.model.banking;

import java.util.ArrayList;

public record Customers(String name, ArrayList<Double> transactions) {
    public Customers(String name, double initialDeposit) {
        this(name.toUpperCase(), new ArrayList<>(500));
        transactions.add(initialDeposit);
    }
}
