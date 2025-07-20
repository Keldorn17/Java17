package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.*;

public final class Bank {

    private final int routingNumber;
    private UUID lastTransactionId;
    private final Map<String, BankCustomer> customers = new LinkedHashMap<>();

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public BankCustomer getCustomer(String id) {
        return customers.get(id);
    }

    public String getCustomerId(String name) {
        for (Map.Entry<String, BankCustomer> entry : customers.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        BankCustomer customer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);
        customers.put(customer.getCustomerId().toString(), customer);
    }

    public synchronized void doTransaction(String id, AccountType type, BigDecimal amount) {
        var customer = customers.get(id);
        checkValidCustomer(customer, id);

        var customerAccount = Optional.ofNullable(customer.getAccount(type))
                .orElseThrow(() -> new IllegalArgumentException("Account Type not found for customer: " + type));

        UUID newTxId = UUID.randomUUID();
        customerAccount.commitTransaction(routingNumber, newTxId,
                customer.getCustomerId().toString(), amount);

        lastTransactionId = newTxId;
    }

    public synchronized void doTransaction(String id, AccountType type, double amount) {
        doTransaction(id, type, BigDecimal.valueOf(amount));
    }

    private void checkValidCustomer(BankCustomer customer, String id) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer ID not found " + id);
        }
    }
}
