import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "SearchProviderToRegister")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        {"M","Juan","Perez","11","September","1980","mail_00@mail.com","Empresa SA","YES","pass00","pass00"}

                };
    }


}
