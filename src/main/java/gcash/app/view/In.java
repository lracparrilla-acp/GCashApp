package gcash.app.view;
import java.util.Arrays;
import java.util.Scanner;

public class In {
    public static final Scanner scanner = new Scanner(System.in);

    public static String readPhoneNumber() {
        int tries = 3;

        while (tries > 0) {
            System.out.print("Enter your number: ");
            String input = scanner.nextLine().trim();

            input = input.replaceAll("\\s+", "");

            if (input.matches("^(\\+63|0)\\d{10}$")) {
                return input.substring(input.length() - 10);
            } else {
                tries--;
                System.out.println("Invalid phone number. Use 0912xxxxxxx or +63912xxxxxxx.");
                System.out.println("Tries left: " + tries);
            }
        }

        System.out.println("Too many failed attempts. Exiting...");
        System.exit(0);

        return null;
    }


    public static char[] readPin() {
        int tries = 3;

        while (tries > 0) {
            System.out.print("Enter your 6-digit PIN: ");
            String pin = scanner.nextLine().trim();

            if (pin.matches("^\\d{6}$")) {
                return pin.toCharArray();
            } else {
                tries--;
                System.out.println("Invalid PIN. It must be exactly 6 digits.");
                System.out.println("Tries left: " + tries);
            }
        }

        System.out.println("Too many failed attempts. Exiting...");
        System.exit(0);
        return null;


    }
}
