package gcash.app.view;
import gcash.app.model.Users;
import gcash.app.repository.DashboardUserDAO;

import java.sql.SQLException;

public class UserDashboardView {
    public static void userDashboardView(String phoneNumber) throws SQLException {

        Users user = DashboardUserDAO.dashboardUserDAO(phoneNumber);

        System.out.println("| ========== Dashboard ========== |");
        if (user != null) {
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
        }
        else{
            System.out.println("Di ko alarm :D .");
        }
    }
}
