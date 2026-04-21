package gcash.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterUserDAO {

    public boolean registerUser(
            Connection connection,
            String id,
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
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, phoneNumber);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, pinHash);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
