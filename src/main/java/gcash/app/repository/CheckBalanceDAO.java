package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
import gcash.app.model.Balance;
import gcash.app.model.Users;
import gcash.app.view.ProgressBar;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalanceDAO {

    public void checkBalance(Users user, Balance balance) throws SQLException, InterruptedException {

        if (balance.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Your balance is: ₱" + balance.getAmount());
        } else {
            recheckBalance(user, balance);
        }
    }

    public void recheckBalance(Users user, Balance balance) throws InterruptedException, SQLException {
        ProgressBar.progressBar();

        String sql = "SELECT amount FROM balances WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setQueryTimeout(5);
            stmt.setObject(1, user.getUuid());

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    balance.setAmount(rs.getBigDecimal("amount"));
                    System.out.println("\n\nYour balance: ₱" + balance.getAmount() + "\n\n");
                }
                else{
                    System.out.println("No record found!!");
                }
            }

        }
    }

}
