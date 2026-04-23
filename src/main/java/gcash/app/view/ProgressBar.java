package gcash.app.view;

public class ProgressBar {

    public static void progressBar() throws InterruptedException {
        double total = 100;
        System.out.println();
        for (int i = 0; i <= total; i++) {
            System.out.print("\r["
                    + repeat("*", i / 2)
                    + repeat(" ", 50 - i / 2)
                    + "] " + i + "%");
            Thread.sleep((long) (5 + Math.random() * 30));
        }
    }

    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder(str.length() * count);
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}