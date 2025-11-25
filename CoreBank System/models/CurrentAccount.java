package models;

public class CurrentAccount extends Account {

    private double overdraftLimit = 5000;

    public CurrentAccount(int id, String name, double balance) {
        super(id, name, balance, "Current");
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
