package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterUserDAO {

    public boolean registerUser(
            UUID id,
            String phoneNumber,
            String firstname,
            String lastname,
            String email,
            String pinHash
    ) {


        String sql = "INSERT INTO users (\n" +
                "    id,\n" +
                "    phone_number,\n" +
                "    firstname,\n" +
                "    lastname,\n" +
                "    email,\n" +
                "    pin_hash\n" +
                ")\n" +
                "VALUES (CAST(? AS UUID), ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setString(1, String.valueOf(id));
            ps.setString(2, phoneNumber);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, pinHash);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
