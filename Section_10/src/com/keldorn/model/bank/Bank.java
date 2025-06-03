package com.keldorn.model.bank;

import java.util.ArrayList;

public class Bank {
    private final String bankName;
    private final ArrayList<Customer> customers;

    public Bank(String bankName) {
        this(bankName, new ArrayList<>());
    }

    public Bank(String bankName, ArrayList<Customer> customers) {
        this.bankName = bankName;
        this.customers = customers;
    }

    public String getBankName() {
        return bankName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addNewCustomer(Customer customer) {
        if (isCustomerExits(customer.getName())) {
            throw new IllegalArgumentException("This customer already exists!");
        }
        customers.add(customer);
    }

    public void customerInformation(String customerName) {
        Customer customer = getCustomer(customerName);
        if (customer == null) {
            throw new IllegalArgumentException("This customer doesn't exists!");
        }
        System.out.println(customer);
    }

    private Customer getCustomer(String customerName) {
        Customer customer = null;
        for (var c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                customer = c;
                break;
            }
        }
        return customer;
    }

    private boolean isCustomerExits(String customerName) {
        return getCustomer(customerName) != null;
    }

    public void addTransaction(String senderName, String receiverName, double transactionAmount) {
        if (transactionAmount <= 0) {
            throw new IllegalArgumentException("Transaction amount cannot be less then 1");
        }
        if (isCustomerExits(senderName) && isCustomerExits(receiverName)) {
            getCustomer(senderName).newTransaction(transactionAmount * -1);
            getCustomer(receiverName).newTransaction(transactionAmount);
        }
    }
}
