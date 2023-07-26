import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek:  region id'si 1 olan satır değerlerini çağırın.
        System.out.println("===Örnek 1===\n");
        String sql1 = "select * from countries where region_id = 1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true döner çünkü select komutu ile data çağrıldı

        //SQL query ile çağrılan datayı görebilmek için execute() methodu yerine executeQuery() methodunu kullanmalıyız.
        /*
        executeQuery() methodu çağrılan datayı ResultSet olarak dönüyor.
        Default olarak sütun isimlerini gösteren bir pointer bulunuyor.
        Dataya ulaşabilmek için bu pointer'ı next() methodu ile ilgili satıra taşımamız gerekiyor.
        next() methodu sırada satır var ise 'true' döner ve pointer'ı sıradaki satıra taşır.
         */

        ResultSet resultSet1 = statement.executeQuery(sql1);

        while (resultSet1.next()) {

            System.out.println(resultSet1.getString("country_id") + " -- " + resultSet1.getString("country_name") + " -- " + resultSet1.getInt("region_id"));

        }

        //2.Örnek: "region_id"si 2'den büyük olan "country_id" ve "country_name" değerlerini çağırın.
        System.out.println("\n===Örnek 2===\n");
        String sql2 = "select * from countries where region_id > 2";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {

            System.out.println(resultSet2.getString("country_id") + " -- " + resultSet2.getString("country_name"));

        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("\n===Örnek 3===\n");
        String sql3 = "select * from companies where number_of_employees = (select min(number_of_employees) from companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);

        while (resultSet3.next()) {

            System.out.println(resultSet3.getInt("company_id") + " -- " + resultSet3.getString("company") + " -- " + resultSet3.getString("number_of_employees"));

        }
    }
}
