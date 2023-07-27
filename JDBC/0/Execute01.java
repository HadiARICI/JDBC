import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'ı kaydet ==> JDBC 4 sonrası yapılmıyor
        //Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");

        //3. Adım: Statement oluştur
        Statement statement = connection.createStatement();

        //4. Adım: Query çalıştır

        //1. Örnek: workers adında bir table oluşturunuz
        boolean sql1 = statement.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println("sql1 = " + sql1);//false döner çünkü "create" komutu kullanıldı ve bir data çağrılmadı.

        /*
        execute() methodu DDL (create, drop, alter, truncate) ile kullanıldığında bir data return etmediği için her zaman 'false' döner
        execute() methodu DQL (select) ile kullanıldığında bir data return ettiğinde 'true' data return etmediğinde 'false' döner
         */

        //2. Örnek: workers table'ına worker_address adında bir sütun ekleyiniz
        String query = "ALTER TABLE workers ADD worker_address VARCHAR(120)";
        boolean sql2 = statement.execute(query);
        System.out.println("sql2 = " + sql2);//false döner çünkü "ALTER" komutu kullanıldı ve bir data çağrılmadı.

        //3. Örnek: workers table'ını siliniz
        boolean sql3 = statement.execute("DROP TABLE workers");
        System.out.println("sql3 = " + sql3);

        //5. Adım: Bağlantıyı kapat
        connection.close();
        statement.close();

    }
}
