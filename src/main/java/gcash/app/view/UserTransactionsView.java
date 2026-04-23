package gcash.app.view;

import gcash.app.model.Users;

import java.math.BigDecimal;

import static gcash.app.view.In.scanner;

public class UserTransactionsView {

    public static void userCashInView(){
        System.out.println("| ============= Cash-In ============= |\n");
        System.out.println("How much would you like to cash in?");
        double amount = scanner.nextDouble();

        //TODO: Implement In class/method to read inputs safely. Use Separate controller class
    }

    public static void userSendMoneyView(){
        System.out.println("| =========== Send Money =========== |\n");
        System.out.print("How much would you like to send?: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter the number of the recipient: ");

        //TODO: Implement In class/method to read inputs safely. Use Separate controller class
    }

    public static double userBalanceView(){
        double amount = 0;
        System.out.println("Your current balance is" + amount);
        return amount;
    }

    public static StringBuilder userTransactionHistoryView(){
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append("2");

        return sb;
    }
}
