package com.keldorn.model.banking;

import java.util.ArrayList;

public class Banking {
    private String name;
    private ArrayList<Customers> customers = new ArrayList<>(5000);

    public Banking(String name) {
        this.name = name;
    }

    private Customers getCustomer(String customerName) {
        for (var customer : customers) {
            if (customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        System.out.printf("Customer (%s) wasn't found %n", customerName);
        return null;
    }

    public void addNewCustomer(String customerName, double initialDeposit) {
        if (getCustomer(customerName) == null) {
            Customers customer = new Customers(customerName, initialDeposit);
            customers.add(customer);
            System.out.println("New Customer added " + customer);
        }
    }

    @Override
    public String toString() {
        return "Banking{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    public void addTransaction(String name, double transactionAmount) {
        Customers customer = getCustomer(name);
        if (customer != null) {
            customer.transactions().add(transactionAmount);
        }
    }

    public void printStatement(String customerName) {
        Customers customer = getCustomer(customerName);
        if (customer == null) {
            return;
        }

        System.out.println("-".repeat(30));
        System.out.println("Customer Name: " + customer.name());
        System.out.println("Transactions:");
        for (double d : customer.transactions()) {
            System.out.printf("$%10.2f (%s)%n", d, d < 0 ? "debit" : "credit");
        }
    }
}
