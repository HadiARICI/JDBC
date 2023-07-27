import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {
    /*
    Given
      User connects to the database
      (Kullanıcı veritabanına bağlanır)

    When
      User sends the query to get the region ids from "countries" table
      (Kullanıcı, 'countries' table'dan 'region id'leri almak üzere query gönderir )

    Then
      Assert that the number of region ids greater than 1 is 17.
      (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

    And
      User closes the connection
    */

    //1. Yol: SQL Query ile satırları sayarak
    @Test
    public void countryTest() throws SQLException {
        //User connects to the database

        //User sends the query to get the region ids from "countries" table
        String sql = "select count(*) from countries where region_id > 1";

        ResultSet resultSet = JDBCUtils.executeQuery(sql);
        resultSet.next();//pointerı header'dan asıl table'a getir
        int numOfRow = resultSet.getInt(1);

        assertEquals(17, numOfRow);

    }

    //2. Yol: region_id'leri bir list içerisine alarak
    @Test
    public void countryTest2() throws SQLException {

        List<Object> idList = JDBCUtils.getColumnList("countries", "region_id");
        System.out.println("idList = " + idList);

        // Assert that the number of region ids greater than 1 is 17.
        int numberOfRows = 0;
        for (Object w : idList) {
            if ((int) w > 1) {
                numberOfRows++;
            }
        }
        System.out.println("numberOfRows = " + numberOfRows);

        assertEquals(17, numberOfRows);

    }

}
