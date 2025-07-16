package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.dto.AccountType;

public final class BankAccount {
    private final AccountType accountType;
    private final double balance;

    public BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public BankAccount(BankAccount bankAccount) {
        this(bankAccount.getAccountType(), bankAccount.getBalance());
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }
}
