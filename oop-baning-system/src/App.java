import java.util.Scanner;

public class App {
    private static int accountCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("======================================");
        System.out.println("      Welcome to the Banking System!  ");
        System.out.println("======================================");

        System.out.print("Enter account type (1 for General, 2 for Savings): ");
        int accountType = scanner.nextInt();
        scanner.nextLine(); 

        String firstName = "";
        String lastName = "";
        double initialBalance = 0;

        if (accountType == 1 || accountType == 2) {
            System.out.print("Enter first name (leave blank for default): ");
            firstName = scanner.nextLine().trim();
            System.out.print("Enter last name (leave blank for default): ");
            lastName = scanner.nextLine().trim();
        }

        if (accountType == 2) {
            System.out.print("Enter initial balance: ");
            initialBalance = scanner.nextDouble();
            scanner.nextLine(); 
        }

        String accountNumber = "ACC-" + (++accountCounter);

        if (accountType == 1) {
            account = new BankAccount(
                firstName.isEmpty() ? "Default" : firstName,
                lastName.isEmpty() ? "Customer" : lastName,
                accountNumber,
                initialBalance
            );
        } else if (accountType == 2) {
            System.out.print("Enter annual interest rate (as a decimal): ");
            double annualInterestRate = scanner.nextDouble();
            account = new SavingsAccount(
                firstName.isEmpty() ? "Default" : firstName,
                lastName.isEmpty() ? "Customer" : lastName,
                accountNumber,
                initialBalance,
                annualInterestRate
            );
        }

        System.out.println("\nAccount created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Balance: $" + account.getBalance());
        System.out.println("======================================");

        while (true) {
            System.out.print("\nChoose an action:\n" +
                    "1 - Deposit\n" +
                    "2 - Withdraw\n" +
                    "3 - Transaction History\n" +
                    "4 - Monthly Process\n" +
                    "5 - Check Balance\n" +
                    "6 - Exit\n" +
                    "Your choice: ");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdraw amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Transaction History:");
                    for (String transaction : account.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 4:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).calculateAnnualInterest();
                    }
                    account.monthlyProcess();
                    System.out.println("Monthly processing done.");
                    System.out.println("Account Balance: $" + account.getBalance());
                    break;
                case 5:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            if (!account.getFirstName().equals("Default")) {
                System.out.println("Hello " + account.getFirstName() + "!");
            }
        }
    }
}
// main run file