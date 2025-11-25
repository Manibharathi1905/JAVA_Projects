package services;

import db.Database;

import java.sql.*;

public class TransactionService {

    public void log(int accountId, String type, double amount) {
        String sql = "INSERT INTO transactions(account_id, type, amount) VALUES(?,?,?)";

        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void showTransactions(int accountId) {
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY date DESC";

        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Transaction History ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("txn_id") + " | " +
                    rs.getString("type") + " | " +
                    rs.getDouble("amount") + " | " +
                    rs.getTimestamp("date")
                );
            }
        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }
}
