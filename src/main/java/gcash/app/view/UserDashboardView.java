package gcash.app.view;
import gcash.app.model.Users;
import gcash.app.repository.DashboardUserDAO;
import java.sql.SQLException;
import static gcash.app.view.In.scanner;

public class UserDashboardView {
    public static void userDashboardView(String phoneNumber) throws SQLException {

        Users user = DashboardUserDAO.dashboardUserDAO(phoneNumber);
        int choice = 0;

        System.out.println("| ========== Dashboard ========== |");
        if (user != null) {
            System.out.println("Welcome " + user.getLastName() + ", " + user.getFirstName());
        }
        else{
            System.out.println("Di ko alarm :D .");
        }
        while (choice != 5) {
            System.out.print("1. Cash-in\n" +
                    "2. Send money\n" +
                    "3. View Balance\n" +
                    "4. View transaction history\n" +
                    "5. Exit\n" +
                    "Enter your choice (1-5): ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.println("Cash-in");
                        UserTransactionsView.userCashInView();
                        break;
                    case 2:
                        System.out.println("Send money");
                        UserTransactionsView.userSendMoneyView();
                        break;
                    case 3:
                        System.out.println("View Balance");
                        UserTransactionsView.userBalanceView();
                        break;
                    case 4:
                        System.out.println("View transaction history");
                        UserTransactionsView.userTransactionHistoryView();
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        System.exit(0);
                    default:
                        System.out.println("Error. Please enter a number between 1-5");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
            }
        }

    }
}
