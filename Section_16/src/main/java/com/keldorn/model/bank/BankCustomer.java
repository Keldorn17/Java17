package main.java.com.keldorn.model.bank;

import main.java.com.keldorn.model.bank.dto.AccountType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class BankCustomer {
    private final String name;
    private final UUID id = UUID.randomUUID();
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        accounts.add(new BankAccount(AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccounts() {
        List<BankAccount> copy = new ArrayList<>();
        for (var account : accounts) {
            copy.add(new BankAccount(account));
        }
        return copy;
    }

    @Override
    public String toString() {
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%s)%n\t%s%n".formatted(name, id.toString(),
                String.join("\n\t", accountStrings));
    }
}
