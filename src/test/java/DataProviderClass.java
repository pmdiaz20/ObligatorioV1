import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "SearchProviderToRegister")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        {"M","Paco","Fernandez","20","October","1982","1pedrofernandez2133@gmail.com","PedroFernandez SA","YES","pedro1234","pedro1234"}

                };
    }


}
