package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    public UUID addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        BankCustomer customer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);
        customers.put(customer.getCustomerId().toString(), customer);
        return customer.getCustomerId();
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
