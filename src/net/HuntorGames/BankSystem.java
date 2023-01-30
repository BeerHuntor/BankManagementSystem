package net.HuntorGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    /*
        Bank managemenet system components:
        Person(which saves first, middle, last name, age and a "social security number"
        Bank (Which saves a list of customers and their checking accounts, max Overdraw Limit)
        Checking Account (Should have current value, methods for withdrawing/depositing money)


        Authentication Login to create a bank?
        NOTES: Opening a checking account should only work via a bank and must require a certain amount of initial deposit
     */

    private static List<Bank> banks = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);
    private static Bank RequestedBank;
    private static int command;
    private static boolean IsLoggedIn = false;

    public static void main(String[] args) {
        Dialog();
    }

    public static void Dialog() {

        while (true) {
            System.out.println("Welcome to the Banking System, What would you like to do?\nPress the corresponding number to navigate");
            System.out.println("1) Open Bank");
            System.out.println("2) Log into Bank");
            System.out.println("3) Check personal bank balance");
            command = scanner.nextInt(); // leaves /n in buffer.
            scanner.nextLine(); //clears /n in buffer??
            if (command == 1) { // open bank
                OpenBankDialogue();
                continue;
            } else if (command == 2) { // log into bank
                if (!IsLoggedIn) {
                    BankLoginDialogue();
                }
                BankLoggedInDialogue();
                //TODO: find a way to keep coming back to the logged in dialogue instead of resulting back at the main menu.
                continue;

            } else if (command == 3) { // check personal account
                String bankName = RequestInputString("What is the name of the bank that you bank with?");
                if (BankExists(bankName)) {
                    String firstName = RequestInputString("What is your first name?");
                    String lastName = RequestInputString("What is your last name?");
                    int ssn = RequestInputInt("What is your Social Security Number?");

                    //TODO: Finish implementing the funtionality of checking user accounts.
                }
            }
            continue;
        }
    }

    private static void BankLoggedInDialogue() {
        System.out.println("Welcome to the Banking System, You are logged in as" + RequestedBank.GetBankName() +
                " What would you like to do?\nPress the corresponding number to navigate");
        System.out.println("1) Register new customer");
        System.out.println("2) See List Of Customers");
        System.out.println("3) Return to main menu");
        command = scanner.nextInt();
        System.out.println(command);
        scanner.nextLine();
        System.out.println(command);
        if (command == 1) { // register new customer
            String firstName = RequestInputString("What is the first name of customer?");
            String middleName = RequestInputString("What is the middle name of the customer?");
            String lastName = RequestInputString("What is the last name of the customer?");
            int age = RequestInputInt("What is the age of the customer?");
            int ssn = RequestInputInt("What is the social security number of the customer?");
            float depositAmount = RequestInputFloat("What is the initial deposit amount?");
            RequestedBank.OpenCheckingAccount(RequestedBank.RegisterCustomer(firstName, middleName, lastName, age, ssn), depositAmount);
            System.out.println("Checking account successful! Account created for " + firstName + " " + middleName + " " + lastName);
            //TODO: return to bank login dialogue.
        } else if (command == 2) { // return customer list
            RequestedBank.GetCustomerList();
        } else if (command == 3) { // return to main menu
            IsLoggedIn = false;
            return;
        }
    }

    private static void BankLoginDialogue() {
        String bankName = RequestInputString("What is the name of the bank you would like to log into?");
        if (BankExists(bankName)) {
            System.out.println("Bank Exists!");
            String managerName = RequestInputString("Please type the name of the bank manager");
            String managerPassword = RequestInputString("Thank you " + managerName + ", Please type the banks password!");
            System.out.println("Checking credentials now.");
            if (RequestedBank.CheckLoginCredentials(managerName, managerPassword)) {
                System.out.println("Successfully Logged into bank!");
                IsLoggedIn = true;
            } else {
                System.out.println("Login details incorrect please try again.");
            }
        } else {
            System.out.println("Sorry we couldn't find a bank with that name. Try creating one first!");
        }
    }

    //Checks the system to see if the bank requested to login exists.
    private static boolean BankExists(String name) {
        for (Bank bank : banks) {
            if (bank.GetBankName().equalsIgnoreCase(name)) {
                RequestedBank = GetBank(bank);
                System.out.println(RequestedBank.GetBankName() + " " + RequestedBank.GetBankManagerName() + " " + RequestedBank.GetBankPassword());
                return true;
            }
        }
        return false;
    }

    private static Bank GetBank(Bank bankToGet) {
        return bankToGet;
    }

    public static void OpenBankDialogue() {
        String bankName = RequestInputString("Please type your bank name");
        String managerName = RequestInputString("Please type the name of the bank manager.");
        String bankPassword = RequestInputString("Please type the banks password.");
        System.out.println("Stand by we are creating your bank for you now: Details are; Bank Name: " + bankName + ", Managaer Name "
                + managerName + ", Bank Password: " + bankPassword);
        System.out.println("Is this correct?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Yes")) {
            CreateBank(bankName, managerName, bankPassword);
        }
    }

    public static void CreateBank(String name, String managerName, String managerPassword) {
        if (banks.contains(name)) {
            System.out.println("Bank already exists! Please log into the managers section to manage your bank. ");
            return;
        }
        banks.add(new Bank(name, managerName, managerPassword));
        System.out.println(banks.get(0).GetBankName());
    }
    
    public static String RequestInputString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public static int RequestInputInt(String prompt){
        System.out.println(prompt);
        return scanner.nextInt();
    }
    public static float RequestInputFloat(String prompt) {
        System.out.println(prompt);
        return scanner.nextFloat();
    }
}