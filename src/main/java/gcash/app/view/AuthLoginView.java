package gcash.app.view;
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

            boolean isValid = dao.authenticateUser(phoneNumber, plainPin);

            if(!isValid){
                System.out.println("Login failed. Check your number/pin");
            }
            else {
                System.out.println("Login successful!");
                userDashboardView(phoneNumber);

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
