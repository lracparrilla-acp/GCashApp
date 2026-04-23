package gcash.app.view;
import gcash.app.model.Users;
import gcash.app.repository.LoginUserDAO;

import java.sql.SQLException;
import java.util.Arrays;

import static gcash.app.view.UserDashboardView.userDashboardView;

public class AuthLoginView {
    static void loginView() {
        System.out.println("| ============== LOGIN ============== |\n");
        String phoneNumber = In.readPhoneNumber();
        System.out.println("You entered: " + phoneNumber);

        char[] pin = In.readPin();
        String plainPin = new String(pin);

        try {
            LoginUserDAO dao = new LoginUserDAO();

            Users user = dao.authenticateUser(phoneNumber, plainPin);

            if(user == null){
                System.out.println("Login failed. Check your number/pin");
                loginView();
            }
            else {
                System.out.println("\n\nLogin successful!\n");
                userDashboardView(user);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.fill(pin, '\0');
            plainPin = null;
        }

        System.exit(0);
    }


}
