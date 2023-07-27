import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: Selamlama yapan bir function oluşturup callable statement ile çağırınız
        //CallableStatement oluşturmak için:

        //1. Adım: Function oluşturma komutunu yaz
        String sql = "create or replace function selamlama(x text) returns text as $$  begin return 'Merhaba ' || x || '. Nasılsın?'; end; $$ language plpgsql;";

        //2. Adım: Function komutunu çalıştır
        statement.execute(sql);

        //3. Adım: Function'ı çağır

        //Normal Function çağırma yöntemi
//        String functionQuery = "select selamlama('Ali')";
//        ResultSet resultSet = statement.executeQuery(functionQuery);
//        resultSet.next();
//        System.out.println(resultSet.getString(1));

        //CallableStatement objesi oluştur
        CallableStatement callableStatement = connection.prepareCall("{? =call selamlama(?)}");

        //4. Adım: Soru işaretlerine atama yap
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2,"Ali");

        //5. Adım: CallableStatement'ı çalıştır
        callableStatement.execute();

        //6. Adım: Datayı CallableStatement'tan çağır
        System.out.println(callableStatement.getString(1));

        //2. Örnek: İki parametreyi toplayan bir function oluşturup callable statement ile çağırınız.
        //1. Adım: Function oluşturma komutunu yaz
        String sql2 = "create or replace function toplama(x int, y int) returns numeric as $$  begin return x+y; end; $$ language plpgsql;";

        //2. Adım: Function komutunu çalıştır
        statement.execute(sql2);

        //3. Adım: Function'ı çağır

        //CallableStatement objesi oluştur
        CallableStatement callableStatement2 = connection.prepareCall("{? =call toplama(?, ?)}");

        //4. Adım: Soru işaretlerine atama yap
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2,6);
        callableStatement2.setInt(3, 7);

        //5. Adım: CallableStatement'ı çalıştır
        callableStatement2.execute();

        //6. Adım: Datayı CallableStatement'tan çağır
        System.out.println(callableStatement2.getObject(1));

    }

}
