package gcash.app.model;
import java.util.UUID;

public class Users {

    static UUID uuid = UUID.randomUUID();
    static String phoneNumber;
    static String firstName;
    static String lastName;
    static String email;
    private String pin;
    static String pinHash;

    //constructor
    public Users(UUID uuid, String email, String lastName, String firstName, String phoneNumber) {
        Users.uuid = uuid;
        Users.email = email;
        Users.lastName = lastName;
        Users.firstName = firstName;
        Users.phoneNumber = phoneNumber;
    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Users.email = email;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Users.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Users.firstName = firstName;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        Users.phoneNumber = phoneNumber;
    }

}

class UserRegistration{

}
