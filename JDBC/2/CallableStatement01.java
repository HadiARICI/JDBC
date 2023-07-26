import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: Selamlama yapan bir function oluşturup callable statement ile çağırınız
        //Callable Statement adımları:

        //1. Adım: Function kodunu yaz
        String sql = "create or replace function selamlama(x TEXT) returns text as $$ begin return 'Merhaba ' || x || ', Nasılsın?'; end; $$ language plpgsql;";

        //2. Adım: Function kodunu çalıştır
        statement.execute(sql);

        //3. Adım: Function'ı çağır

        //Aşağıda normal çağırma yöntemi kullanıldı
//        String sqlFunction = "select selamlama('Ali')";
//        ResultSet resultSet = statement.executeQuery(sqlFunction);
//        resultSet.next();
//        System.out.println(resultSet.getString(1));

        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");

        //4. Adım: Soru işaretleri yerine atama yap
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "Ayşe");

        //5. Adım: callableStatement'ı çalıştır
        callableStatement.execute();

        //6. Adım: Datayı callableStatement'tan çağır
        System.out.println(callableStatement.getObject(1));

        //2. Örnek: İki parametreyi toplayan bir function oluşturup callable statement ile çağırınız.
        //1. Adım: Function kodunu yaz
        String sql2 = "create or replace function toplama(x int, y int) returns NUMERIC as $$ begin return x+y; end; $$ language plpgsql;";

        //2. Adım: Function kodunu çalıştır
        statement.execute(sql2);

        //3. Adım: Function'ı çağır
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?, ?)}");

        //4. Adım: Soru işaretleri yerine atama yap
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 6);
        callableStatement2.setInt(3, 9);

        //5. Adım: callableStatement'ı çalıştır
        callableStatement2.execute();

        //6. Adım: Datayı callableStatement'tan çağır
        System.out.println(callableStatement2.getObject(1));

    }
}
