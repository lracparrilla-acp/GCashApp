package gcash.app.repository;
import gcash.app.config.DatabaseConnection;
import gcash.app.model.Users;
import gcash.app.view.ProgressBar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;
import java.util.UUID;

public class LoginUserDAO {

    public Users authenticateUser(String phoneNumber, String plainPin) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "SELECT id, phone_number, firstname, lastname, email, pin_hash FROM users WHERE phone_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setQueryTimeout(5);
            stmt.setString(1, phoneNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("pin_hash");
                    ProgressBar.progressBar();
                    if(encoder.matches(plainPin,storedHash)){
                        Users user = new Users();
                        user.setUuid(UUID.fromString(rs.getString("id")));
                        user.setPhoneNumber((rs.getString("phone_number")));
                        user.setFirstName(rs.getString("firstname"));
                        user.setLastName(rs.getString("lastname"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}