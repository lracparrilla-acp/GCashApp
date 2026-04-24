package gcash.app.repository;

import gcash.app.config.DatabaseConnection;
import gcash.app.view.ProgressBar;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendMoneyDAO {
    public void sendMoney(String senderPhone, String recipientPhone, BigDecimal amount) throws InterruptedException, SQLException {


        if (senderPhone == null || recipientPhone == null){
            System.out.println("Invalid recipient. Try again");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Amount cant be less than zero!");
        }

        assert senderPhone != null;
        String senderId = senderPhone;

        assert recipientPhone != null;
        String recipientId = recipientPhone;

        if(senderId.equals(recipientId)){
            System.out.println("You cannot send money to yourself foo!");
        }

        String selectBalanceSql = "SELECT amount FROM balances WHERE phone_number = ?";
        String updateBalanceSql = "UPDATE balances SET amount = ? WHERE phone_number = ?";

        Connection conn = null;

        try{
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            BigDecimal senderBalance;
            BigDecimal recipientBalance;

            try(PreparedStatement stmt = conn.prepareStatement(selectBalanceSql)){
                stmt.setObject(1, senderId);

            try(ResultSet rs = stmt.executeQuery()){
                if(!rs.next()){
                    conn.rollback();
                    System.out.println("Sender balance not found.");
                }
                senderBalance = rs.getBigDecimal("amount");
            }


        } try(PreparedStatement stmt = conn.prepareStatement(selectBalanceSql)){
            stmt.setObject(1, recipientId);
            try(ResultSet rs = stmt.executeQuery()){
                if (!rs.next()){
                    conn.rollback();
                    System.out.println("Recipient not found");
                }
                recipientBalance = rs.getBigDecimal("amount");
            }
            }
            BigDecimal newSenderBalance = senderBalance.subtract(amount);
            BigDecimal newRecipientBalance = recipientBalance.add(amount);

            int senderUpdate;
            try(PreparedStatement stmt = conn.prepareStatement(updateBalanceSql)){
                stmt.setBigDecimal(1, newSenderBalance);
                stmt.setObject(2, senderId);
                senderUpdate = stmt.executeUpdate();
            }

            int recipientUpdate;
            try(PreparedStatement stmt = conn.prepareStatement(updateBalanceSql)){
                stmt.setBigDecimal(1, newRecipientBalance);
                stmt.setObject(2, recipientId);
                recipientUpdate = stmt.executeUpdate();
            }

            if(senderUpdate == 1 && recipientUpdate == 1){
                conn.commit();
                ProgressBar.progressBar();
                System.out.println("\nTransfer successful!");
                System.out.println("Sent: ₱" + amount);
                System.out.println("New balance: ₱" + newSenderBalance);
            } else {
                conn.rollback();
                System.out.println("Transfer failed. Try again later :D");
            }
        }
        catch (SQLException e) {
            if(conn != null) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        }finally {
            if(conn != null){
                conn.setAutoCommit(true);
                conn.close();
            }
        }


    }
}
