package gcash.app.view;
import gcash.app.repository.LoginUserDAO;
import gcash.app.utils.security.PasswordHasher;
import java.util.Arrays;

import static gcash.app.view.In.scanner;

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
            System.out.println("Login success: " + isValid);

        } finally {
            Arrays.fill(pin, '\0');
            plainPin = null;
        }

        System.exit(0);
    }


}
