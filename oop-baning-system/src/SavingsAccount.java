public class SavingsAccount extends BankAccount {
    private boolean status;
    private double annualInterestRate;
    private int numberOfWithdrawals;

    public SavingsAccount(String firstName, String lastName, String accountNumber, double initialBalance, double annualInterestRate) {
        super(firstName, lastName, accountNumber, initialBalance);
        this.annualInterestRate = annualInterestRate;
        this.status = true; 
        this.numberOfWithdrawals = 0;
    }

    @Override
    public void withdraw(double amount) {
        if (!status) {
            System.out.println("Withdrawal failed: Account is inactive.");
            return;
        }
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            numberOfWithdrawals++;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
            checkStatus(); 
        } else {t
            System.out.println("Withdrawal failed: Insufficient balance or invalid amount.");
        }
    }

    public void calculateAnnualInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
        transactionHistory.add("Interest earned: $" + monthlyInterest);
        System.out.println("Interest calculated: $" + monthlyInterest);
    }

    @Override
    public void monthlyProcess() {
        super.monthlyProcess(); 
        if (numberOfWithdrawals > 4) {
            int excessWithdrawals = numberOfWithdrawals - 4;
            double serviceCharge = 5 + (excessWithdrawals * 1);
            balance -= serviceCharge;
            transactionHistory.add("Service charge applied: $" + serviceCharge);
            System.out.println("Service charge applied: $" + serviceCharge);
        } else {
            balance -= 5;
            transactionHistory.add("Monthly service charge applied: $5");
            System.out.println("Monthly service charge applied: $5");
        }
        numberOfWithdrawals = 0; 
        checkStatus(); 
    }

    private void checkStatus() {
        if (balance < 25) {
            status = false; 
            System.out.println("Account status: INACTIVE (balance below $25).");
        } else {
            status = true; 
            System.out.println("Account status: ACTIVE.");
        }
    }
}
