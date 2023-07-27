import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Runner {

    public static void main(String[] args) throws SQLException {


        //JDBCUtils.connectToDatabase();
        //JDBCUtils.createStatement();

        //Execute methodu çalıştır
        JDBCUtils.execute("select * from companies");//execute() methodu query'yi çalıştırır amd datayı dönmez. Data dönüyorsa true dönmüyorsa false döner

        //Companies table'ının tüm sütunlarını yazdır
        ResultSet resultSet = JDBCUtils.executeQuery("select * from companies");//executeQuery() methodu query'yi çalıştırır ve dönen datayı bir ResultSet içine koyar.

        while (resultSet.next()) {
            System.out.println(resultSet.getObject("company"));
        }

        //companies table'ının company adlı sütunun değerlerini list olarak yazdır
        List<Object> list = JDBCUtils.getColumnList("companies", "company");
        System.out.println("list = " + list);

        //companies table'ının tamamını yazdırınız
        System.out.println(JDBCUtils.getQueryResultMap("select * from companies"));

        JDBCUtils.closeConnection();

    }
}
