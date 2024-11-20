package ca.bcit.BankManagement;
import java.util.ArrayList;

/**
 * Represents a bank that manages a collection of bank accounts.
 * Provides functionalities to add accounts, retrieve accounts by ID,
 * and calculate the total balance of all accounts.
 */
public class Bank {

    private final ArrayList<BankAccount> accountList;

    public Bank()
    {
        this.accountList = new ArrayList<>();
    }

    /**
     * Adds a new account to the bank.
     *
     * @param account the bank account to be added
     * @throws IllegalArgumentException if an account with the same ID already exists
     */
    void addAccount(final BankAccount account)
    {
        for (BankAccount existingAccount : accountList)
        {
            if (existingAccount.getAccountId() == account.getAccountId())
            {
                throw new IllegalArgumentException("Account ID already exists.");
            }
        }
        accountList.add(account);
    }

    /**
     * Retrieves a bank account by its account ID.
     *
     * @param accountId the unique ID of the account to retrieve
     * @return the bank account with the specified ID
     * @throws IllegalArgumentException if no account with the given ID is found
     */
    public BankAccount retrieveAccount(final int accountId)
    {
        for (BankAccount account : accountList)
        {
            if (account.getAccountId() == accountId)
            {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found.");
    }

    /**
     * Calculates the total balance of all accounts in the bank.
     *
     * @return the total balance in USD of all accounts
     */
    public int totalBalanceUsd()
    {
        int total = 0;

        for (BankAccount account : accountList)
        {
            total += account.getCurrentBalance();
        }
        return total;
    }
}
