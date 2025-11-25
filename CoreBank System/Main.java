import models.Account;
import services.AccountService;
import services.TransactionService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AccountService accService = new AccountService();
        TransactionService txnService = new TransactionService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CORE BANK SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Balance");
            System.out.println("6. View Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Account Type (Savings/Current): ");
                    String type = sc.nextLine();

                    System.out.print("Initial Deposit: ");
                    double bal = sc.nextDouble();

                    int id = accService.createAccount(name, type, bal);

                    if (id != -1) System.out.println("Account Created. ID = " + id);
                    break;
                }

                case 2: {
                    System.out.print("Account ID: ");
                    int id = sc.nextInt();

                    Account acc = accService.getAccount(id);

                    if (acc == null) { System.out.println("Invalid Account"); break; }

                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    acc.deposit(amt);
                    accService.updateBalance(id, acc.getBalance());
                    txnService.log(id, "DEPOSIT", amt);

                    System.out.println("Deposit Successful");
                    break;
                }

                case 3: {
                    System.out.print("Account ID: ");
                    int id = sc.nextInt();
                    Account acc = accService.getAccount(id);

                    if (acc == null) { System.out.println("Invalid Account"); break; }

                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    if (acc.withdraw(amt)) {
                        accService.updateBalance(id, acc.getBalance());
                        txnService.log(id, "WITHDRAW", amt);
                        System.out.println("Withdrawal Successful");
                    } else {
                        System.out.println("Insufficient Balance");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Sender ID: ");
                    int sId = sc.nextInt();
                    Account sender = accService.getAccount(sId);

                    System.out.print("Receiver ID: ");
                    int rId = sc.nextInt();
                    Account receiver = accService.getAccount(rId);

                    if (sender == null || receiver == null) {
                        System.out.println("Invalid Account");
                        break;
                    }

                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    if (sender.withdraw(amt)) {
                        receiver.deposit(amt);

                        accService.updateBalance(sId, sender.getBalance());
                        accService.updateBalance(rId, receiver.getBalance());

                        txnService.log(sId, "TRANSFER_OUT", amt);
                        txnService.log(rId, "TRANSFER_IN", amt);

                        System.out.println("Transfer Successful");
                    } else {
                        System.out.println("Insufficient Funds");
                    }
                    break;
                }

                case 5: {
                    System.out.print("Account ID: ");
                    int id = sc.nextInt();
                    Account acc = accService.getAccount(id);

                    if (acc == null) { System.out.println("Invalid Account"); break; }

                    System.out.println("\nBalance: ₹" + acc.getBalance());
                    break;
                }

                case 6: {
                    System.out.print("Account ID: ");
                    int id = sc.nextInt();
                    txnService.showTransactions(id);
                    break;
                }

                case 0:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
