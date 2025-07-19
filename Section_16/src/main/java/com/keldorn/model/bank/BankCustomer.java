package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.enums.AccountType;

import java.util.*;

public final class BankCustomer {
    private final String name;
    private final UUID customerId = UUID.randomUUID();
    private final List<BankAccount> accounts = new ArrayList<>();

    BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        accounts.add(new BankAccount(AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public BankAccount getAccount(AccountType type) {
        BankAccount found = null;
        for (var account : accounts) {
            if (account.getAccountType() == type) {
                found = account;
                break;
            }
        }
        return found;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%s)%n\t%s%n".formatted(name, customerId.toString(),
                String.join("\n\t", accountStrings));
    }
}
