import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTestWithLogin extends BaseTest {

    @BeforeMethod
    @Parameters({"valid_user", "valid_password"})
    public void setupLogin(String userName, String password){

        loginPage = homePage.clickInLogIn();
        loginPage.login(userName,password);

    }

}
