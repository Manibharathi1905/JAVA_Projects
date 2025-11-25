package services;

import db.Database;
import models.*;

import java.sql.*;

public class AccountService {

    public int createAccount(String name, String type, double balance) {
        if (!type.equals("Savings") && !type.equals("Current")) {
            System.out.println("Invalid account type. Must be 'Savings' or 'Current'.");
            return -1;
        }

        String sql = "INSERT INTO accounts(name, account_type, balance) VALUES(?,?,?)";

        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setDouble(3, balance);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
        return -1;
    }

    public Account getAccount(int id) {
        try {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String type = rs.getString("account_type");
                String name = rs.getString("name");
                double bal = rs.getDouble("balance");

                if (type.equals("Savings"))
                    return new SavingsAccount(id, name, bal);

                return new CurrentAccount(id, name, bal);
            }
        } catch (Exception e) {
            System.out.println("Error fetching account: " + e.getMessage());
        }
        return null;
    }

    public void updateBalance(int id, double balance) {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
}
