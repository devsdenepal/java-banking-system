import java.util.ArrayList;

public class SavingsAccount extends BankAccount {
    private boolean status;
    private double annualInterestRate;
    private int numberOfWithdrawals;

    public SavingsAccount(String firstName, String lastName, String accountNumber, double initialBalance, double annualInterestRate) {
        super(firstName, lastName, accountNumber, initialBalance);
        this.annualInterestRate = annualInterestRate;
        this.status = initialBalance >= 25;
        this.numberOfWithdrawals = 0;
    }

    @Override
    public void withdraw(double amount) {
        if (status) {
            super.withdraw(amount);
            if (amount > 0) {
                numberOfWithdrawals++;
            }
            checkStatus(); 
        } else {
            System.out.println("Withdrawal failed: Account is inactive.");
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        checkStatus(); 
    }

    @Override
    public void monthlyProcess() {
        if (numberOfWithdrawals > 4) {
            double serviceCharge = (numberOfWithdrawals - 4) * 1.0;
            double totalCharge = 5.0 + serviceCharge;
            if (getBalance() >= totalCharge) {
                double newBalance = getBalance() - totalCharge;
                setBalance(newBalance);
            } else {
                System.out.println("Insufficient balance to cover service charges.");
            }
        }
        numberOfWithdrawals = 0; 
        checkStatus(); 
    }

    public void calculateAnnualInterest() {
        if (status) {
            double monthlyInterestRate = annualInterestRate / 12;
            double monthlyInterest = getBalance() * monthlyInterestRate;
            deposit(monthlyInterest);
        }
    }

    public boolean isActive() {
        return status;
    }

    private void checkStatus() {
        if (getBalance() < 25) {
            status = false; 
        } else {
            status = true; 
        }
    }
}
