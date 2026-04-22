package gcash.app.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.configure()
            .directory(".")
            .filename(".env")
            .ignoreIfMalformed()
            .load();

    private static final String HOST = requireEnv("RDS_HOSTNAME");
    private static final String PORT = requireEnv("RDS_PORT");
    private static final String DATABASE_NAME = requireEnv("RDS_DB_NAME");
    private static final String DB_USER = requireEnv("RDS_USERNAME");
    private static final String DB_PASSWORD = requireEnv("RDS_PASSWORD");

    private static final String URL =
            "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Postgres driver not found. ", e);
        }
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    private static String requireEnv(String key) {
        String value = dotenv.get(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Missing required env variable: " + key);
        }
        return value;
    }
}