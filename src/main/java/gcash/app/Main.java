package gcash.app;
import gcash.app.view.AuthMenuView;

import static gcash.app.view.In.scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("| ========== Mini DigiBank ========== |");
        System.out.println();
//        scanner.nextLine();
        AuthMenuView.mainMenu();

    }
}

