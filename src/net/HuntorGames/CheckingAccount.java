package net.HuntorGames;

public class CheckingAccount{

    private float balance;
    private final float minimumDepositLimit = 10.0f;
    private float overdraftLimit;
    public CheckingAccount(){}
    public CheckingAccount(float initialDeposit)
    {
        if (initialDeposit >= minimumDepositLimit) {
            this.balance = initialDeposit;
        }
    }

    public void WithdrawMoney(float amountToWithdraw) {
        if (balance < amountToWithdraw)
        {
            System.out.println("Insufficient Funds in your account!");
        } else {
            balance -= amountToWithdraw;
            System.out.println("You have successfully withdrawn " + "£" + amountToWithdraw +  ".  Your balance is now " + "£" + balance);
        }

    }
    public void DepositMoney(float amountToDeposit)
    {
        System.out.println("You have successfully deposited " + "£" + amountToDeposit +  ".  Your balance is now " + "£" + balance);
        balance += amountToDeposit;
    }

    public float getBalance() {
        return balance;
    }

    public float getOverdraftLimit() {
        return overdraftLimit;
    }
}
