import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        //1. Yol: OFFSET ve LIMIT kullanarak
        String sql1 = "select * from companies order by number_of_employees desc offset 1 limit 1";
        ResultSet resultSet1 = statement.executeQuery(sql1);

        while (resultSet1.next()) {

            System.out.println(resultSet1.getString("company") + " -- " + resultSet1.getInt("number_of_employees"));

        }

        //2. Yol: Sub query kullanarak
        String sql2 = "select * from companies where number_of_employees = (select max(number_of_employees) from companies where number_of_employees < (select max(number_of_employees) from companies))";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("company") + " -- " + resultSet2.getInt("number_of_employees"));
        }

    }
}
