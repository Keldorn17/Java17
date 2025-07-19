package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.enums.AccountType;
import main.java.com.keldorn.model.bank.dto.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BankAccount {
    private final AccountType accountType;
    private BigDecimal balance;
    private final Map<UUID, Transaction> transactions = new HashMap<>();

    public BankAccount(AccountType accountType, BigDecimal balance) {
        this.accountType = accountType;
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    public BankAccount(AccountType accountType, double balance) {
        this(accountType, BigDecimal.valueOf(balance));
    }

    public BankAccount(BankAccount bankAccount) {
        this(bankAccount.getAccountType(), bankAccount.getBalance());
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Map<UUID, Transaction> getTransactions() {
        return Map.copyOf(transactions);
    }

    void commitTransaction(int routingNumber, UUID transactionId, String customerId, BigDecimal amount) {
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        if (balance.add(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transaction.");
        }
        transactions.put(transactionId,
                new Transaction(routingNumber, transactionId, UUID.fromString(customerId), amount));

        balance = balance.add(amount);
    }

    void commitTransaction(int routingNumber, UUID transactionId, String customerId, double amount) {
        commitTransaction(routingNumber, transactionId, customerId, BigDecimal.valueOf(amount));
    }

    @Override
    public String toString() {
        return "%s $%.2f%n%s".formatted(accountType, balance, transactions.values());
    }
}
