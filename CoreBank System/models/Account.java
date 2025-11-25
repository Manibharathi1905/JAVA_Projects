package models;

public abstract class Account {
    protected int accountId;
    protected String name;
    protected double balance;
    protected String accountType;

    public Account(int accountId, String name, double balance, String accountType) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int getAccountId() { return accountId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);
}
