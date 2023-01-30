package net.HuntorGames;

public class Customer {

    private final String FirstName;
    private final String  MiddleName;
    private final String  LastName;
    private final int  SocialSecurityNumber;
    private int Age;

    public Customer (String firstName, String middleName, String lastName, int age, int socialSecurityNumber)
    {
        this.FirstName = firstName;
        this.MiddleName = middleName;
        this.LastName = lastName;
        this.Age = age;
        this.SocialSecurityNumber = socialSecurityNumber;
    }

    public String GetFirstName() {return FirstName;}
    public String GetMiddleName() {return MiddleName;}
    public String GetLastName() {return LastName;}
    public int GetAge() {return Age;}
    public int GetSSN() {return SocialSecurityNumber;}

}
