package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;

public class LoginUserDAO {

    public boolean authenticateUser(String phoneNumber, String plainPin) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "SELECT pin_hash FROM users WHERE phone_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setQueryTimeout(5);
            stmt.setString(1, phoneNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("pin_hash");
                    return encoder.matches(plainPin, storedHash);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}