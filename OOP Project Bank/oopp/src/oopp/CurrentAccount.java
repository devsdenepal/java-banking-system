package oopp;

import java.io.Serializable;

public class CurrentAccount extends Account implements Serializable
{
    private double minBalance;

    public CurrentAccount()
    {
        super();
        minBalance = 0.0;
    }
    
    public CurrentAccount(String IBAN, double balance, double balanceLimit, double transactionLimit, String accountType,  double minBalance)
    {
        super(IBAN,  balance, balanceLimit, transactionLimit, accountType);
        this.minBalance = minBalance;
    }
    


    @Override
    public double withdraw(double amount)
    {   double b = balance;
        if ((amount <= balance) && ((b -= amount) < minBalance) && (amount <= transactionLimit))
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
    public  double deposit(double amount) {
        System.out.println("Entered");
        this.balance = this.balance + amount;
        
    	 if ((this.balance += amount) <= this.balanceLimit && amount <= this.transactionLimit)
         {
             this.balance += amount;
             System.out.println("Transaction Successful!");
             return this.balance;
         }

         else
         {
             System.out.println("Amount exceeds Limit! Transaction Failed!");
             return this.balance;
         }
    }
}
