package gcash.app.repository;

import gcash.app.config.DatabaseConnection;
import gcash.app.model.Transactions;
import gcash.app.model.Users;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


public class TransactionHistoryDAO {

    public void cashInTransactionHistory(UUID userId, BigDecimal amount) throws RuntimeException{
        Transactions transactions = new Transactions();
        String sql = "INSERT INTO transactions " +
                "(transaction_id, amount, type, to_user_id, transaction_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql);

            stmt.setObject(1, transactions.getRefNum());
            stmt.setBigDecimal(2, amount);
            stmt.setString(3, "cashin");
            stmt.setObject(4, userId);
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(transactions.getUpdatedAt()));

            int transactionRows = stmt.executeUpdate();

            if (transactionRows > 0){
                conn.commit();
                System.out.println("Ref. #: " + transactions.getRefNum() + "\n\n");
            }
            else{
                conn.rollback();
                System.out.println("Cash-in transaction NOT recorded. Try again later!");
            }


        } catch (SQLException e) {
            if (conn != null){
                try{
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }finally {
            try{
                if (stmt != null) stmt.close();
                if (conn != null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public void displayTransactionHistory(){

    }
}
