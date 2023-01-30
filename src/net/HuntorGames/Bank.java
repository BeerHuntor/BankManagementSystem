package net.HuntorGames;

import java.util.HashMap;
import java.util.Map;


public class Bank extends CheckingAccount{

    HashMap<Customer, CheckingAccount> customers = new HashMap<>();
    private final String BankName;
    private String BankManager;
    private String BankPassword;

    public Bank(String bankName, String bankManager, String bankPassword) {
        this.BankName = bankName;
        this.BankManager = bankManager;
        this.BankPassword = bankPassword;
    }


    //Saves list of customers and their checking accounts.

    public void OpenCheckingAccount(Customer customer, float initialDeposit) {
        if (!HasAccount(customer)) {
            customers.put(customer, new CheckingAccount(initialDeposit));
        }
    }

    public Customer RegisterCustomer(String firstName, String middleName, String lastName, int age, int socialSecurityNumber)
    {
        return new Customer(firstName, middleName, lastName, age, socialSecurityNumber);
    }

    public Boolean HasAccount(Customer customer) {
        return customers.containsKey(customer);
    }

    public Customer GetCustomerAccount(String firstName, String lastName, int ssn)
    {
        for (Map.Entry<Customer, CheckingAccount> customer : customers.entrySet())
        {
            if (customer.getKey().GetFirstName().equalsIgnoreCase(firstName) && customer.getKey().GetLastName().equalsIgnoreCase(lastName) && customer.getKey().GetSSN() == ssn)
            {
                return customer.getKey();
            }
        }
        System.out.println("Sorry no customer accounts match those details. Try creating an account with a bank first.");
        return null;
    }
    public void GetCustomerList()
    {
        for(Map.Entry<Customer, CheckingAccount> customer : customers.entrySet() )
        {
            System.out.println("Full Name: " + customer.getKey().GetFirstName() + " " + customer.getKey().GetMiddleName() + " " + customer.getKey().GetLastName() +
                    " Age: " + customer.getKey().GetAge() + " SSN: " + customer.getKey().GetSSN() + " Account Balance: £" + customer.getValue().getBalance());
        }
    }


    public String GetBankName() {
        return BankName;
    }
    public String GetBankManagerName(){
        return BankManager;
    }
    public String GetBankPassword() {
        return BankPassword;
    }

    public boolean CheckLoginCredentials(String managerName, String managerPassword){
        return BankManager.equalsIgnoreCase(managerName) && BankPassword.equalsIgnoreCase(managerPassword);
    }


}

