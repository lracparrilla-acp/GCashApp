package gcash.app.view;

import static gcash.app.view.In.scanner;

public class AuthMenuView {
    public static void mainMenu() {

        int choice = 0;

        while (choice != 3) {
            System.out.print("| ============ MAIN MENU ============ |\n" +
                    "1. Login\n" +
                    "2. Register\n" +
                    "3. Exit\n" +
                    "Enter your choice (1-3): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        AuthLoginView.loginView();
                        break;
                    case 2:
                        RegisterView.registerView();
                        break;
                    case 3:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Error: Please enter a number between 1 and 3.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
            }
        }
    }
}
