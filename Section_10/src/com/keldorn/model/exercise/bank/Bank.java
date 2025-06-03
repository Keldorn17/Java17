package com.keldorn.model.exercise.bank;

import java.util.ArrayList;

public class Bank {
    private final String name;
    private final ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        Branch branch = findBranch(branchName);
        boolean isNewBranch = branch == null;
        if (isNewBranch) {
            branches.add(new Branch(branchName));
        }
        return isNewBranch;
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        Branch branch = findBranch(branchName);
        boolean success = false;
        if (branch != null) {
            success = branch.newCustomer(customerName, initialTransaction);
        }
        return success;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        Branch branch = findBranch(branchName);
        boolean success = false;
        if (branch != null) {
            success = branch.addCustomerTransaction(customerName, transaction);
        }
        return success;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        Branch branch = findBranch(branchName);
        boolean isBranchExists = branch != null;
        if (isBranchExists) {
            System.out.printf("Customer details for branch %s%n", branchName);
            var branchCustomers = branch.getCustomers();
            int i = 1;
            for (var customer : branchCustomers) {
                System.out.printf("Customer: %s[%d]%n", customer.getName(), i++);

                if (printTransactions) {
                    int j = 1;
                    System.out.println("Transactions");
                    for (var transaction: customer.getTransactions()) {
                        System.out.printf("[%d] Amount %.2f%n", j++, transaction);
                    }
                }
            }
        }
        return isBranchExists;
    }

    private Branch findBranch(String branchName) {
        Branch branch = null;
        for (var b : branches) {
            if (b.getName().equalsIgnoreCase(branchName)) {
                branch = b;
                break;
            }
        }
        return branch;
    }
}
