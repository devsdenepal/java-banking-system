// CurrentAccount.java
public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountNumber, String accountHolderName, double balance, double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) {
            balance -= amount;
            System.out.println(amount + " withdrawn using overdraft. New Balance: " + balance);
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount.");
        }
    }
}
