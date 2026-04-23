package gcash.app.view;

import gcash.app.repository.RegisterUserDAO;
import gcash.app.utils.security.PasswordHasher;
import java.util.UUID;
import static gcash.app.view.In.scanner;

public class AuthRegisterView {


    static void registerView() {
        System.out.println("| ============ REGISTER ============ |");

        String phoneNumber = readPhoneNumber();
        System.out.println("Phone: " + phoneNumber);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        char[] pin = readPin();
        String plainPin = new String(pin);
        String pinHash = PasswordHasher.hashPin(plainPin);
        UUID uuid = UUID.randomUUID();

        try {
            RegisterUserDAO dao = new RegisterUserDAO();
            boolean isRegistered = dao.registerUser(uuid, phoneNumber, firstName, lastName, email, pinHash);

            if (isRegistered) {
                System.out.println("\nRegistration successful!!");
                System.out.println("Remember your number: " + phoneNumber);
                System.out.println("DO NOT FORGET YOUR PIN!");
            } else {
                System.out.println("Registration failed. Phone number may already exist.");
                registerView();
            }

        } finally {
            java.util.Arrays.fill(pin, '\0');
            AuthLoginView.loginView();
        }

        System.exit(0);
    }

    static String readPhoneNumber() {
        System.out.print("Enter your phone number (09xxxxxxxxx or +639xxxxxxxxx): ");
        String input = scanner.nextLine().trim();

        if (input.startsWith("+63")) {
            input = input.substring(3);
        }

        if (input.startsWith("0")) {
            input = input.substring(1);
        }

        if (!input.matches("^9\\d{9}$")) {
            System.out.println("Phone number format must be 09xxxxxxxxx or +639xxxxxxxxx");
            scanner.nextLine();
            readPhoneNumber();
        }

        return input;
    }

    static char[] readPin() {
        java.io.Console console = System.console();
        System.out.print("Enter your 6-digit PIN: ");
        String input = scanner.nextLine().trim();

        if (input.length() != 6 || !input.matches("\\d{6}")) {
            System.out.println("Invalid PIN. Must be 6 digits.");
            return readPin();
        }

        return input.toCharArray();
    }
}