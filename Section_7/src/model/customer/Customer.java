package model.customer;

import model.customer.defaults.DefaultCustomerData;

public class Customer {

    private final String name;
    private final int creditLimit;
    private final String emailAddress;

    public Customer(String name, int creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public Customer() {
        this(DefaultCustomerData.DEFAULT_NAME, DefaultCustomerData.DEFAULT_EMAIL);
    }

    public Customer(String name, String emailAddress) {
        this(name, DefaultCustomerData.DEFAULT_CREDIT_LIMIT, emailAddress);
    }

    public String getName() {
        return name;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
