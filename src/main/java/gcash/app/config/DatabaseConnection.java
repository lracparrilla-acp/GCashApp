package gcash.app.config;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;
    static Dotenv dotenv = Dotenv.load();

    private static final String HOST = dotenv.get("RDS_HOSTNAME");
    private static final String PORT = dotenv.get("RDS_PORT");
    private static final String DATABASE_NAME = dotenv.get("RDS_DB_NAME");

    private static final String DB_USER = dotenv.get("RDS_USERNAME");
    private static final String DB_PASSWORD = dotenv.get("RDS_PASSWORD");
}
