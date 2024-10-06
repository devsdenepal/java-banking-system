// Main.java
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create a customer
        Customer customer1 = new Customer("C001", "John Doe");

        // Create accounts
        SavingsAccount savings = new SavingsAccount("SA001", "John Doe", 1000, 5);
        CurrentAccount current = new CurrentAccount("CA001", "John Doe", 500, 200);

        // Add accounts to customer
        customer1.openAccount(savings);
        customer1.openAccount(current);

        // Add customer to bank
        bank.addCustomer(customer1);

        // Perform operations
        savings.deposit(500);
        savings.addInterest();
        current.withdraw(600);

        // Check balance
        System.out.println("Savings Balance: " + savings.checkBalance());
        System.out.println("Current Balance: " + current.checkBalance());
    }
}
