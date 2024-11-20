package ca.bcit.BankManagement;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest
{
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        // Initialize banks and accounts
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount(12345, 1000); // ID: 12345, Initial Balance: 1000
        account2 = new BankAccount(67890, 500);  // ID: 67890, Initial Balance: 500

        // Add accounts to respective banks
        bank1.addAccount(account1);
        bank2.addAccount(account2);
    }

    @Test
    void depositIncreasesBalanceAndVerify() {
        account1.deposit(200); // Add 200 to account1
        assertEquals(1200, account1.getCurrentBalance());

        account2.deposit(300); // Add 300 to account2
        assertEquals(800, account2.getCurrentBalance());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify() {
        account1.withdraw(200); // Subtract 200 from account1
        assertEquals(800, account1.getCurrentBalance());

        account2.withdraw(100); // Subtract 100 from account2
        assertEquals(400, account2.getCurrentBalance());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException() {
        // Trying to withdraw more than the current balance should throw an exception
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(1200); // More than balance
        });
        assertEquals("Overdraft restriction", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            account2.withdraw(600); // More than balance
        });
        assertEquals("Overdraft restriction", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank() {
        BankAccount newAccount = new BankAccount(54321, 100); // New account
        bank2.addAccount(newAccount);

        BankAccount retrievedAccount = bank2.retrieveAccount(54321);
        assertEquals(newAccount, retrievedAccount);
    }

    @Test
    void handlingInvalidAccountRetrieval() {
        // Retrieving a non-existent account should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bank1.retrieveAccount(99999); // Non-existent account ID
        });
        assertEquals("Account not found.", exception.getMessage());
    }

    @Test
    void totalBalanceCalculationForBanks() {
        // Total balance for bank1 should match account1's balance
        assertEquals(1000, bank1.totalBalanceUsd());

        // Add a new account and verify total balance
        bank1.addAccount(new BankAccount(33333, 200));
        assertEquals(1200, bank1.totalBalanceUsd());
    }

}
