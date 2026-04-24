package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterUserDAO {

    public boolean registerUser(UUID id, String phoneNumber, String firstname, String lastname, String email, String pinHash
    ) {
        String userSql = "INSERT INTO users (\n" +
                "    id,\n" +
                "    phone_number,\n" +
                "    firstname,\n" +
                "    lastname,\n" +
                "    email,\n" +
                "    pin_hash\n" +
                ")\n" +
                "VALUES (CAST(? AS UUID), ?, ?, ?, ?, ?)";

        String balSql = "INSERT INTO balances (\n" +
                "   user_id\n," +
                " amount\n," +
                " phone_number)\n" +
                "VALUES(CAST(? AS UUID), ?, ?)";

        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement balStmt = null;


        try{
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            userStmt = conn.prepareStatement(userSql);
            balStmt = conn.prepareStatement(balSql);


            userStmt.setObject(1, id);
            userStmt.setString(2, phoneNumber);
            userStmt.setString(3, firstname);
            userStmt.setString(4, lastname);
            userStmt.setString(5, email);
            userStmt.setString(6, pinHash);

            int userRows = userStmt.executeUpdate();

            balStmt.setString(1, String.valueOf(id));
            balStmt.setBigDecimal(2, (BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP));
            balStmt.setString(3, phoneNumber);

            int balRows = balStmt.executeUpdate();

            if(userRows > 0 && balRows > 0){
                conn.commit();
                return true;
            }
            else{
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            if(conn != null){
                try{
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return false;
        } finally {
            try{
                if (balStmt != null) balStmt.close();
                if (userStmt != null) userStmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                 e.printStackTrace();
            }
        }
    }
}
