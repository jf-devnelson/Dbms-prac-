import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBConnection.java
 * Handles the connection to the MySQL database.
 * Implemented as a Singleton so only one connection instance is reused.
 *
 * Reads credentials from db.properties so you never have to hardcode
 * passwords inside the Java source files.
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // Private constructor prevents external instantiation (Singleton pattern)
    private DBConnection() throws SQLException, IOException, ClassNotFoundException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        // Loads the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() {
        try {
            if (instance == null || instance.connection == null || instance.connection.isClosed()) {
                instance = new DBConnection();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return instance.connection;
    }

    public static void closeConnection() {
        try {
            if (instance != null && instance.connection != null && !instance.connection.isClosed()) {
                instance.connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
