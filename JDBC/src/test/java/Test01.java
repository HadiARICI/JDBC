public class Test01 {
    public static void main(String[] args) {
        //Test, beklenen ve asıl datanın karşılaştırılmasıdır.

        String expectedData = "Hello";
        String actualData = "Hello";

        if(actualData.equals(expectedData)){
            System.out.println("Test PASSED");
        }else {
            System.out.println("Test FAILED");
        }

        //Otomasyon testi yapabilmek için "Test Framework" kullanmamız gerekir(JUnit, TestNG, Cucumber)

    }

}
