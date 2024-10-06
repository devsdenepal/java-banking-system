import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base BankAccount class
class BankAccount {
    private static int accountNumberCounter = 1; // Counter for account numbers
    protected String accountNumber;
    protected String customerFirstName;
    protected String customerLastName;
    protected double balance;
    protected int numberOfDepositPerMonth;
    protected int numberOfWithdrawPerMonth;
    protected double monthlyServiceCharge;
    protected int transactionCount;

    // Constructor 1: No first/last name, default balance
    public BankAccount() {
        this("NoFirstName", "NoLastName", 0.0);
    }

    // Constructor 2: With first/last name, default balance
    public BankAccount(String firstName, String lastName) {
        this(firstName, lastName, 0.0);
    }

    // Constructor 3: With first/last name and opening balance
    public BankAccount(String firstName, String lastName, double initialBalance) {
        this.customerFirstName = firstName;
        this.customerLastName = lastName;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + accountNumberCounter++;
        this.transactionCount = 0;
        this.numberOfWithdrawPerMonth = 0;
        this.numberOfDepositPerMonth = 0;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            numberOfDepositPerMonth++;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            numberOfWithdrawPerMonth++;
            transactionCount++;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Withdrawal failed. Insufficient balance.");
        }
    }

    // Monthly process method
    public void monthlyProcess() {
        transactionCount = 0;
        numberOfWithdrawPerMonth = 0;
        numberOfDepositPerMonth = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getFirstName() {
        return customerFirstName;
    }

    public String getLastName() {
        return customerLastName;
    }
}

// SavingsAccount class extending BankAccount
class SavingsAccount extends BankAccount {
    private boolean status;
    private double annualInterestRate;
    private double monthlyServiceCharge = 5.0;

    // Constructor 1: No first/last name, default balance, interest rate, and status
    public SavingsAccount() {
        super();
        this.status = true;
        this.annualInterestRate = 0.02; // Default interest rate
    }

    // Constructor 2: With first/last name, default balance, interest rate, and status
    public SavingsAccount(String firstName, String lastName) {
        super(firstName, lastName);
        this.status = true;
        this.annualInterestRate = 0.02; // Default interest rate
    }

    // Constructor 3: With first/last name, opening balance, interest rate, and status
    public SavingsAccount(String firstName, String lastName, double initialBalance, double interestRate) {
        super(firstName, lastName, initialBalance);
        this.status = initialBalance > 25; // Set status based on initial balance
        this.annualInterestRate = interestRate;
    }

    // Method to calculate and apply the monthly interest based on annual interest rate
    public void calculateAnnualInterest() {
        if (this.status) {
            double monthlyInterestRate = annualInterestRate / 12;
            double monthlyInterest = getBalance() * monthlyInterestRate;
            deposit(monthlyInterest);
            System.out.println("Monthly interest applied: $" + monthlyInterest);
        } else {
            System.out.println("Account is inactive, interest not applied.");
        }
    }

    // Override withdraw method to handle status deactivation if balance < 25
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            super.withdraw(amount);
            if (getBalance() < 25) {
                this.status = false;
                System.out.println("Account deactivated due to low balance.");
            }
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    // Override monthlyProcess method to apply service charges for withdrawals and inactivity
    @Override
    public void monthlyProcess() {
        int excessWithdrawals = numberOfWithdrawPerMonth - 4;
        double totalServiceCharge = monthlyServiceCharge;

        // Apply $1 charge for each withdrawal over 4
        if (excessWithdrawals > 0) {
            totalServiceCharge += excessWithdrawals;
        }

        if (getBalance() >= totalServiceCharge) {
            balance -= totalServiceCharge;
            System.out.println("Service charges applied: $" + totalServiceCharge);
        } else {
            System.out.println("Insufficient balance to cover service charges.");
            balance = 0;
        }

        if (getBalance() < 25) {
            status = false;
            System.out.println("Account deactivated due to low balance after service charges.");
        }

        numberOfWithdrawPerMonth = 0;
        numberOfDepositPerMonth = 0;
        transactionCount = 0;
    }
}

// Main application class
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>();

        // Account creation menu
        while (true) {
            System.out.println("\nBank Account Management System");
            System.out.println("1. Create Bank Account");
            System.out.println("2. Create Savings Account");
            System.out.println("3. Manage Existing Account");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            if (option == 4) break;

            if (option == 1 || option == 2) {
                // Collect account details from user
                scanner.nextLine(); // Consume newline
                System.out.print("Enter first name (or leave blank): ");
                String firstName = scanner.nextLine();
                if (firstName.isBlank()) firstName = "NoFirstName";

                System.out.print("Enter last name (or leave blank): ");
                String lastName = scanner.nextLine();
                if (lastName.isBlank()) lastName = "NoLastName";

                BankAccount account;
                if (option == 1) {
                    // Create Bank Account
                    account = new BankAccount(firstName, lastName);
                    System.out.println("Bank Account created successfully.");
                } else {
                    // Create Savings Account
                    System.out.print("Enter opening balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Enter annual interest rate (e.g. 0.02 for 2%): ");
                    double interestRate = scanner.nextDouble();
                    account = new SavingsAccount(firstName, lastName, balance, interestRate);
                    System.out.println("Savings Account created successfully.");
                }
                accounts.add(account);
                System.out.println("Account Number: " + account.getAccountNumber());

            } else if (option == 3) {
                // Managing existing accounts
                if (accounts.isEmpty()) {
                    System.out.println("No accounts found.");
                    continue;
                }
                System.out.print("Enter your account number: ");
                String accNumber = scanner.next();
                BankAccount account = accounts.stream()
                        .filter(a -> a.getAccountNumber().equals(accNumber))
                        .findFirst()
                        .orElse(null);

                if (account == null) {
                    System.out.println("Account not found.");
                    continue;
                }

                // Menu for account actions
                while (true) {
                    System.out.println("\nAccount Management");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Apply Monthly Interest (Savings Only)");
                    System.out.println("5. Apply Monthly Process");
                    System.out.println("6. Exit to Main Menu");
                    System.out.print("Choose an action: ");
                    int action = scanner.nextInt();

                    if (action == 6) break;

                    switch (action) {
                        case 1:
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 2:
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            account.withdraw(withdrawAmount);
                            break;
                        case 3:
                            System.out.println("Current Balance: $" + account.getBalance());
                            break;
                        case 4:
                            if (account instanceof SavingsAccount) {
                                ((SavingsAccount) account).calculateAnnualInterest();
                            } else {
                                System.out.println("This option is only available for Savings Accounts.");
                            }
                            break;
                        case 5:
                            account.monthlyProcess();
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Bank Account Management System.");
    }
}
