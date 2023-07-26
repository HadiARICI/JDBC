import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static Connection connection;
    private static Statement statement;


    public static Connection connectToDatabase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }

    public static Statement createStatement (){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }
}
