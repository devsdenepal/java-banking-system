package oopp;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable
{
    private float interestRate;

    public SavingAccount()
    {
        super();
        interestRate = 0.0f;
    }
    
    public SavingAccount(String IBAN, double balance, double balanceLimit, double transactionLimit,String accountType, float interestRate) {
    	super(IBAN,balance,balanceLimit,transactionLimit, accountType);
    	this.interestRate = interestRate;
    }

    @Override
    public double withdraw(double amount)
    {
        if (amount <= balance && amount <= transactionLimit)
        {
            balance -= amount;
            System.out.println("Transaction Successful!");
            return balance;
        }

        else
        {
            System.out.println("Amount exceeds Balance! Transaction Failed!");
            return balance;
        }
    }

    @Override
    public double deposit(double amount)
    {
        if ((balance += amount) <= balanceLimit && amount <= transactionLimit)
        {
            balance += amount;
            System.out.println("Transaction Successful!");
            return balance;
        }

        else
        {
            System.out.println("Amount exceeds Limit! Transaction Failed!");
            return balance;
        }
    }
}
