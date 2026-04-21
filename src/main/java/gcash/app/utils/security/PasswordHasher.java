package gcash.app.utils.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPin(String plainPin) {
        return encoder.encode(plainPin);
    }

    public static boolean verifyPin(String plainPin, String hashedPin) {
        return encoder.matches(plainPin, hashedPin);
    }

}
