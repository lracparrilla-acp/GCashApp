package gcash.app.view;
import gcash.app.model.Balance;
import gcash.app.model.Users;
import gcash.app.repository.CashInDAO;
import gcash.app.repository.CheckBalanceDAO;

import java.math.BigDecimal;
import java.sql.SQLException;

import static gcash.app.view.In.scanner;


public class UserTransactionsView {


    public static void userCashInView(Users user) throws SQLException, InterruptedException {
        System.out.println("| ============= Cash-In ============= |\n");
        Balance balance = new Balance();
        CashInDAO cashInDAO = new CashInDAO();

        System.out.println("How much would you like to cash in?");
        BigDecimal amount = scanner.nextBigDecimal();

        cashInDAO.cashIn(user, balance, amount);

        //TODO: Implement In class/method to read inputs safely. Use Separate controller class
    }

    public static void userSendMoneyView(Users user){
        System.out.println("| =========== Send Money =========== |\n");
        System.out.print("How much would you like to send?: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter the number of the recipient: ");

        //TODO: Implement In class/method to read inputs safely. Use Separate controller class
    }

    public static void userBalanceView(Users user) throws InterruptedException, SQLException {
        Balance balance = new Balance();
        CheckBalanceDAO checkBalanceDAO = new CheckBalanceDAO();
        checkBalanceDAO.checkBalance(user, balance);
    }

    public static void userTransactionHistoryView(Users user){

    }
}
