import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {

    private static Connection connection;//Connection variable'ın başka methodlarda da kullanılabilmesi için 'class level'da belirtiliyor.
    private static Statement statement;
    private static ResultSet resultSet;


    //Bu method database'e bağlanıp bir Connection data return edecek
    public static Connection connectToDatabase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


    //Medunna'ya bağlanan method
//    public static Connection connectToDatabase() {
//
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return connection;
//    }

    //Bu method connectToDatabase() methodu çağırarak statement dönüyor
    public static Statement createStatement() {

        try {
            statement = connectToDatabase().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }

    //Bu method String bir SQL query'yi createStatement() methodu çağırarak çalıştırıp boolean dönüyor
    public static boolean execute(String sql) {

        try {
            return createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Bu method String bir SQL query'yi createStatement() methodu çağırarak çalıştırıp ResultSet dönüyor
    public static ResultSet executeQuery(String sql){

        try {
            resultSet = createStatement().executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    //Bu method paramterede belirtilen table ve column ismine göre bir query çalıştırıp ilgili sütun objelerini bir list olarak dönüyor
    public static List<Object> getColumnList(String table, String column) throws SQLException {

        List<Object> list = new ArrayList<>();
        String query = "select " + column + " from " + table;

        ResultSet resultSet = executeQuery(query);
        while (resultSet.next()) {
            list.add(resultSet.getObject(column));
        }

        return list;
    }

    //Bu method bağlantıyı kapatır
    public static void closeConnection(){

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Map<String, Object>> getQueryResultMap(String query) throws SQLException {
        resultSet = createStatement().executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return rowList;
    }




}
