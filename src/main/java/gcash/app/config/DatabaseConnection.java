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

    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;

    //private constructor.
    private DatabaseConnection(){
    }

    public static Connection getConnection(){

        try{
            if (connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            }

        }
        catch (ClassNotFoundException e){
            System.out.println("Class not found error.");
        }
        catch (SQLException e){
            System.out.println("Db connection error.");
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
       catch (SQLException e) {
            System.out.println("Cant close db connection.");
            throw new RuntimeException(e);
        }
    }


}
