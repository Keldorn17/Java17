package com.keldorn.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount bankAccount;
    private static int count;

    @BeforeAll
    static void beforeAll() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }

    @BeforeEach
    void setup() {
        bankAccount = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @Test
    void deposit() {
        double balance = bankAccount.deposit(200, true);
        assertEquals(1200.00, balance, 0);
    }

    @Test
    void withdraw_branch() {
        double balance = bankAccount.withdraw(600, true);
        assertEquals(400, balance, 0);
    }

    @Test
    void withdraw_notBranch() {
        assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(600, false);
            fail("Should have thrown an IllegalArgumentException");
        });
    }

    @Test
    void getBalance_deposit() {
        bankAccount.deposit(200, true);
        assertEquals(1200.00, bankAccount.getBalance(), 0);
    }

    @Test
    void getBalance_withdraw() {
        bankAccount.withdraw(200, true);
        assertEquals(800.00, bankAccount.getBalance(), 0);
    }

    @Test
    void isChecking_true() {
        assertTrue(bankAccount.isChecking(), "The account is NOT a checking account");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("This executes after any test cases. Count = " + count);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Count = " + count++);
    }
}