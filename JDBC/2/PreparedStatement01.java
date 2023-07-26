import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //PreparedStatement oluşturmak için:

        //1. Adım: PreparedStatement query'sini oluştur
        String sql1 = "update companies set number_of_employees = ? where company = ?";//? --> parametrelendire yapar

        //2. Adım: PreparedStatement objesi oluştur.
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);

        //3. Adım: Soru işaretleri yerine atamalar yap
        preparedStatement.setInt(1, 9999);
        preparedStatement.setString(2, "IBM");

        //4. Adım: Query'yi çalıştır
        int guncellenenSatirSayisi = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncellenmiş table'ı okuyalım
        String sql2 = "select * from companies";
        ResultSet resultSet1 = statement.executeQuery(sql2);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getObject(1) + "--" + resultSet1.getObject(2) + "--" + resultSet1.getObject(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5000 olarak güncelleyin.

        //Soru işaretleri yerine atamalar yap
        preparedStatement.setInt(1, 5000);
        preparedStatement.setString(2, "GOOGLE");

        // Query'yi çalıştır
        preparedStatement.executeUpdate();

        //Güncellenmiş table'ı okuyalım
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }
    }
}
