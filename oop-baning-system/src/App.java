import java.util.Scanner;

public class App {
    private static int accountCounter = 0;

    // ANSI color codes
    private static final String RESET = "\033[0m"; // Reset to default
    private static final String GREEN = "\033[0;32m"; // Green
    private static final String RED = "\033[0;31m"; // Red
    private static final String YELLOW = "\033[0;33m"; // Yellow
    private static final String CYAN = "\033[0;36m"; // Cyan
    private static final String MAGENTA = "\033[0;35m"; // Magenta

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println(CYAN + "\n==================================================");
        System.out.println("            WELCOME TO THE BANKING SYSTEM        ");
        System.out.println("==================================================" + RESET + "\n");

        System.out.print(GREEN + "Select account type:\n1. General Account\n2. Savings Account\nEnter choice: " + RESET);
        int accountType = scanner.nextInt();
        scanner.nextLine();

        String firstName = "";
        String lastName = "";
        double initialBalance = 0;

        if (accountType == 1 || accountType == 2) {
            System.out.print(GREEN + "Enter first name (leave blank for default): " + RESET);
            firstName = scanner.nextLine();
            System.out.print(GREEN + "Enter last name (leave blank for default): " + RESET);
            lastName = scanner.nextLine();
        }

        if (accountType == 2) {
            System.out.print(GREEN + "Enter initial balance: " + RESET);
            initialBalance = scanner.nextDouble();
            scanner.nextLine();
        }

        String accountNumber = "ACC-" + (++accountCounter);

        if (accountType == 1) {
            account = new BankAccount(firstName.isEmpty() ? "Default" : firstName,
                                       lastName.isEmpty() ? "Customer" : lastName,
                                       accountNumber,
                                       initialBalance);
        } else if (accountType == 2) {
            System.out.print(GREEN + "Enter annual interest rate (as a decimal): " + RESET);
            double annualInterestRate = scanner.nextDouble();
            account = new SavingsAccount(firstName.isEmpty() ? "Default" : firstName,
                                          lastName.isEmpty() ? "Customer" : lastName,
                                          accountNumber,
                                          initialBalance,
                                          annualInterestRate);
        }

        System.out.println(YELLOW + "\nAccount created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Balance: $" + account.getBalance() + RESET + "\n");

        while (true) {
            System.out.println(MAGENTA + "Choose action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transaction History");
            System.out.println("4. Monthly Process");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: " + RESET);

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    System.out.print(GREEN + "Enter deposit amount: " + RESET);
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposited: $" + depositAmount);
                    break;
                case 2:
                    System.out.print(GREEN + "Enter withdraw amount: " + RESET);
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
                    System.out.println(RED + "Exiting..." + RESET);
                    scanner.close();
                    return;
                default:
                    System.out.println(RED + "Invalid option, please try again." + RESET);
            }

            if (!account.getFirstName().equals("Default")) {
                System.out.println("Hello " + account.getFirstName() + "!");
            }

            if (account instanceof SavingsAccount) {
                System.out.println("Account Status: " + (((SavingsAccount) account).isActive() ? "Active" : "Inactive"));
            }

            System.out.println("\n" + CYAN + "==================================================" + RESET);
        }
    }
}
