package gcash.app.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gcash.app.config.DatabaseConnection;
import gcash.app.model.Users;

public class DashboardUserDAO {
    public static Users dashboardUserDAO(String phoneNumber) throws SQLException {
        String sql = "SELECT firstname, lastname, email FROM users WHERE phone_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setQueryTimeout(5);
            stmt.setString(1, phoneNumber);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    String email = rs.getString("email");
                    return new Users(firstName, lastName, email);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("Failed(?)");
        return null;
    }

}
