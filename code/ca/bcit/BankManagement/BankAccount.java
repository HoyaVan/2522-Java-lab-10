package ca.bcit.BankManagement;

/**
 * Represents a bank account with a unique account ID and balance.
 * Provides methods for depositing, withdrawing, and validating account information.
 */
public class BankAccount {

    private final int MIN_ACCOUNT_ID = 0;
    private final int MAX_ACCOUNT_ID = 99999;
    private int accountId;
    private int currentBalance;

    /**
     * Constructs a new BankAccount with the specified account ID and initial balance.
     *
     * @param accountId      the unique ID of the bank account
     * @param currentBalance the initial balance of the bank account in USD
     * @throws IllegalArgumentException if the account ID is invalid
     */
    public BankAccount(final int accountId,
                       final int currentBalance)
    {
        validateAccountID(accountId);

        this.accountId = accountId;
        this.currentBalance = currentBalance;
    }

    /**
     * Returns the account ID of the bank account.
     *
     * @return the account ID
     */
    public int getAccountId()
    {
        return accountId;
    }

    /**
     * Returns the current balance of the bank account in USD.
     *
     * @return the current balance
     */
    public int getCurrentBalance()
    {
        return currentBalance;
    }

    /**
     * Sets the account ID of the bank account.
     *
     * @param accountId the new account ID
     */
    public void setAccountId(final int accountId)
    {
        this.accountId = accountId;
    }

    /**
     * Sets the current balance of the bank account.
     *
     * @param currentBalance the new balance in USD
     */
    public void setCurrentBalance(final int currentBalance)
    {
        this.currentBalance = currentBalance;
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param usdAmount the amount to deposit in USD
     * @throws IllegalArgumentException if the deposit amount is invalid
     */
    void deposit(final int usdAmount)
    {
        validateUsdAmount(usdAmount);

        currentBalance += usdAmount;
    }

    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param usdAmount the amount to withdraw in USD
     * @throws IllegalArgumentException if the withdrawal amount is invalid or exceeds the balance
     */
    void withdraw(final int usdAmount)
    {
        validateUsdAmount(usdAmount);

        if (usdAmount > currentBalance)
        {
            throw new IllegalArgumentException("Overdraft restriction");
        }

        currentBalance -= usdAmount;
    }

    /**
     * Returns a formatted string representing the account ID and current balance.
     *
     * @return a string representation of the account details
     */
    public String getBalanceUsd()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Account name : ")
                .append(accountId)
                .append(" , your current balance is ")
                .append(currentBalance)
                .append("\n");
        return sb.toString();
    }

    /**
     * Validates the specified account ID to ensure it is within the allowable range.
     *
     * @param accountID the account ID to validate
     * @throws IllegalArgumentException if the account ID is invalid
     */
    private void validateAccountID(final int accountID)
    {
        if (accountID < MIN_ACCOUNT_ID || accountID > MAX_ACCOUNT_ID)
        {
            throw new IllegalArgumentException("Account ID must be between " + MIN_ACCOUNT_ID +
                    " and " + MAX_ACCOUNT_ID + ": " + accountID);
        }
    }

    /**
     * Validates the specified USD amount to ensure it is positive.
     *
     * @param usdAmount the amount to validate
     * @throws IllegalArgumentException if the amount is less than or equal to zero
     */
    void validateUsdAmount(final int usdAmount)
    {
        if (usdAmount <= 0)
        {
            throw new IllegalArgumentException("Amount of money must be positive");
        }
    }
}
