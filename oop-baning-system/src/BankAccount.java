import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    protected String accountNumber;
    protected String customerFirstName;
    protected String customerLastName;
    protected double balance;
    protected int transactionCount;
    protected List<String> transactionHistory;

    public BankAccount(String firstName, String lastName, String accountNumber, double initialBalance) {
        this.customerFirstName = firstName;
        this.customerLastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionCount = 0;
        this.transactionHistory = new ArrayList<>();
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionCount++;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Withdrawal failed: Insufficient balance or invalid amount.");
        }
    }

    public void monthlyProcess() {
        // Reset monthly transaction count
        transactionCount = 0;
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
}
