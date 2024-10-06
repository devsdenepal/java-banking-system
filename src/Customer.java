// Correct structure of Customer.java

import java.util.ArrayList; // This should be at the top, outside of any class

public class Customer {
    private String customerID;
    private String customerName;
    private ArrayList<BankAccount> accounts;

    // Constructor
    public Customer(String customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.accounts = new ArrayList<>();
    }

    // Open an account
    public void openAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account opened for " + customerName);
    }

    // Close an account
    public void closeAccount(String accountNumber) {
        accounts.removeIf(account -> accountNumber.equals(accountNumber));
        System.out.println("Account closed: " + accountNumber);
    }

    // Get all accounts
    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}
