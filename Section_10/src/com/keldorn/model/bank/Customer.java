package com.keldorn.model.bank;

import java.util.ArrayList;

public class Customer {
    private final String name;
    private final ArrayList<Double> transactions;

    public Customer(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void newTransaction(double value) {
        transactions.add(value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Customer name: %s \nTransactions:\n", name));
        for (var transaction : transactions) {
            stringBuilder.append(String.format("%s%n", transaction));
        }
        return stringBuilder.toString();
    }
}
