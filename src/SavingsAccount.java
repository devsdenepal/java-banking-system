// SavingsAccount.java
public class SavingsAccount extends BankAccount {
    private double interestRate;

    // Constructor
    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);  // Call the parent constructor
        this.interestRate = interestRate;
    }

    // Add Interest Method
    public void addInterest() {
        double interest = balance * (interestRate / 100);
        deposit(interest);
        System.out.println("Interest added: " + interest);
    }
}
