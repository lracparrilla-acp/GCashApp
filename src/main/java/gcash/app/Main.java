package gcash.app;
import gcash.app.view.AuthMenuView;

import static gcash.app.view.In.scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("| ========== Mini DigiBank ========== |");
        System.out.println();
        AuthMenuView.mainMenu();

        //TODO: send money, transaction history
        //TODO: Implement In class/method to read inputs safely. Use Separate controller class
        //TODO: Implement SOLID, DRY, SoC principles
        //TODO: Implement MVC

    }
}
