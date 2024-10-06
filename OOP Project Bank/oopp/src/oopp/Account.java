package oopp;

import java.io.Serializable;


public class Account implements Serializable
{
    protected String IBAN;
    protected double balance;
    protected double balanceLimit;
    protected double transactionLimit;
    protected String accountType;

    public Account()
    {
    	IBAN = null;
        balance = 0.0;
        balanceLimit = 0.0;
        transactionLimit = 0.0;
        accountType = null;
    }
    
	public Account(String IBAN, double balance, double balanceLimit, double transactionLimit, String accountType)
    {
    	this.IBAN = IBAN;
        this.balance = balance;
        this.balanceLimit = balanceLimit;
        this.transactionLimit = transactionLimit;
        this.accountType = accountType;
    }
    

    public double getBalanceLimit() {
		return balanceLimit;
	}


	public double getTransactionLimit() {
		return transactionLimit;
	}
    
    public String getIBAN() {
    	return this.IBAN;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public String getaccountType() {
		return this.accountType;
	}

    public double withdraw(double amount) {
    	 if ((amount <= balance) && (amount <= transactionLimit))
         {
             balance -= amount;
             System.out.println("Transaction Successful!");
             return balance;
         } else 
         {
            System.out.println("Amount exceeds Balance! Transaction Failed!");
            return balance;
         }
    }
   
    public  double deposit(double amount) {
    	
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