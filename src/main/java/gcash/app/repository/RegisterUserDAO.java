package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
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

//        String balSql = "INSERT INTO balances (\n" +
//                "   id\n," +
//                "phone_number\n" +
//                ", amount)\n" +
//                "VALUES(CAST(? AS UUID), ?, ?)";


        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(userSql))

        {

            ps1.setString(1, String.valueOf(id));
            ps1.setString(2, phoneNumber);
            ps1.setString(3, firstname);
            ps1.setString(4, lastname);
            ps1.setString(5, email);
            ps1.setString(6, pinHash);

            int rows = ps1.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
