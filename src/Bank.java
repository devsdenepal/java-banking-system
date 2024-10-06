import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customers;

    public Bank() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer);
    }

    public void removeCustomer(String customerID) {
        customers.removeIf(customer -> customerID.equals(customerID));
        System.out.println("Customer removed: " + customerID);
    }

    public Customer findCustomer(String customerID) {
        for (Customer customer : customers) {
            if (customerID.equals(customerID)) {
                return customer;
            }
        }
        return null;
    }
}