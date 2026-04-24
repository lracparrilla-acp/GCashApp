package gcash.app.utils.security;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ReferenceNumberGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyMMdd-HHmmss");

    //private constructor
    private ReferenceNumberGenerator() {
    }

    public static String generateReferenceNumber() {
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String randomPart = randomUppercase() + randomDigits(2);
        return "TXN-" + timestamp + "-" + randomPart;
    }

    private static String randomUppercase() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char c = (char) ('A' + RANDOM.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }

    private static String randomDigits(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateReferenceNumber());
    }

}
