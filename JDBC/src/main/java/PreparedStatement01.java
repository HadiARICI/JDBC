import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: PreparedStatement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //Prepared statement oluşturmak için:

        //1. Adım: PreparedStatement query'si oluştur
        //update companies set number_of_employees = 9999 where company = 'IBM'
        String sql1 = "update companies set number_of_employees = ? where company = ?";//? --> parametrelendirme

        //2. Adım: PreparedStatement objesi oluştur
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

        //3. Adım: Soru işaretleri yerine ilgili atamaları yap
        preparedStatement1.setInt(1, 9999);
        preparedStatement1.setString(2, "IBM");

        //4. Adım: Query'yi çalıştır
        int guncellenenSatirSayisi = preparedStatement1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncel table'ı okuyalım
        String sql2 = "select * from companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5000 olarak güncelleyin.

        preparedStatement1.setInt(1, 5000);
        preparedStatement1.setString(2, "GOOGLE");

        int guncelleneSatirSayisi2 = preparedStatement1.executeUpdate();
        System.out.println("guncelleneSatirSayisi2 = " + guncelleneSatirSayisi2);

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getInt("company_id") + "--" + resultSet2.getString("company") + "--" + resultSet2.getInt("number_of_employees"));
        }

        connection.close();
        statement.close();

    }
}
