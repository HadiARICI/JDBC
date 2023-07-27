import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JunitTest {


    @Test
    public void test01() {
        //Test geçti veya kaldı gibi dönüt alabilmek için doğrulama(assertion) yapmamız gerekir.
        //Assertion türleri:
        int expectedData = 5;
        int actualData = 5;

        assertEquals(expectedData, actualData);//assertEquals() --> parantez içindeki paramtereler eşit ise test geçer
        assertTrue("hello".contains("ll"));//assertTrue() --> parantez içindeki değer 'true' dönerse test geçer
        //Negative Test
        Assert.assertNotEquals(2, 5);//assertNotEquals() --> parantez içindeki paramtereler eşit değil ise test geçer
        Assert.assertFalse(actualData > 6);//assertFalse() --> parantez içindeki değer 'false' dönerse test geçer

    }

    @Test
    public void test02() {


    }

    @Test
    public void test03() {


    }

}
