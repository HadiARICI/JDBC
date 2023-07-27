import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

   When
     User sends the query to get the created room
     (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

   Then
     Assert that room is created properly
     (Odanın düzgün kaydedildiğini doğrular)

   And
     User closes the connection
  */

    @Test
    public void medunnaTest() throws SQLException {

//        User connects to the database


//        User sends the query to get the created room
        String sql = "select * from room where room_number = 32655790";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);
        resultSet.next();
        String description = resultSet.getString("description");
        System.out.println("description = " + description);

//        Assert that room is created properly
        assertEquals("171 NT Database Test İçin Oluşturuldu", description);

//        User closes the connection
        JDBCUtils.closeConnection();

    }
}
