import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String customerFirstName;
    private String customerLastName;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String firstName, String lastName, String accountNumber, double initialBalance) {
        this.customerFirstName = firstName;
        this.customerLastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
        } else {
            System.out.println("Withdrawal failed: Insufficient balance.");
        }
    }

    public void monthlyProcess() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public String getFirstName() {
        return customerFirstName;
    }

    public String getLastName() {
        return customerLastName;
    }

    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }
}
