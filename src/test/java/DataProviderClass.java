import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "SearchProviderToRegister")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        {"M","Juan","Perez","11","September","1980","mail_089@mail.com","Empresa SA","YES","pass00","pass00"}

                };
    }


    @DataProvider(name = "UserOkWithProduct")
    public static Object[][] getDataFromDataProviderUserOkWithProduct(){
        return new Object[][]
                {
                        {"mail_00@mail.com", "pass00","Asus N551JK-XO076H Laptop","Uruguay","Montevideo",
                                "Avenida Italia 1221","Avenida Italia 1221 Bis","12400","26095815","26095817"}
                };
        }


}
