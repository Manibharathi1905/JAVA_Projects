package models;

public class SavingsAccount extends Account {

    public SavingsAccount(int id, String name, double balance) {
        super(id, name, balance, "Savings");
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
