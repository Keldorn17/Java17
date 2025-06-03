package com.keldorn.model.exercise.bank;

import java.util.ArrayList;

public class Branch {
    private final String name;
    private final ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        customers = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }

    public boolean newCustomer(String customerName, double initialTransaction) {
        Customer customer = findCustomer(customerName);
        boolean isNewCustomer = customer == null;
        if (isNewCustomer) {
            customers.add(new Customer(customerName, initialTransaction));
        }
        return isNewCustomer;
    }

    public boolean addCustomerTransaction(String customerName, double transaction) {
        Customer customer = findCustomer(customerName);
        boolean isCustomerExits = customer != null;
        if (isCustomerExits) {
            customer.addTransaction(transaction);
        }
        return isCustomerExits;
    }

    private Customer findCustomer(String customerName) {
        Customer customer = null;
        for (var c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                customer = c;
                break;
            }
        }
        return customer;
    }
}
