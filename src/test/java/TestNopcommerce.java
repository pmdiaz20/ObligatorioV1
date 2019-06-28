import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.RegisterResultPage;

import java.util.concurrent.TimeUnit;


public class TestNopcommerce {

    //http://demo.nopcommerce.com/


    /*
    CP1: Registrar Usuario:
    1. Navegar a Regiter.
    2. Completar datos obligatorios
    3. Realizar el registro
    4. Verificar que el registro fue satisfactorio
     */

    private WebDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;
    private RegisterResultPage registerResultPage;
    private LoginPage loginPage;
    String valid_email;
    String valid_pass;
    SoftAssert SA;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"valid_email", "valid_pass","browser"})
    public void setupTest(String valid_email, String valid_pass, String browser){

        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get("http://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
        SA = new SoftAssert();
        this.valid_email = valid_email;
        this.valid_pass = valid_pass;

    }
/*


    @Test //esto es porque me exige que sea solo en chrome
    @Parameters("browser")
    public void testBrowser(String browser){
        Assert.assertEquals(browser,"chrome");
    } */

    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testRegister(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword) throws InterruptedException{

        registerPage = homePage.clickInRegister();

        SA.assertTrue(registerPage.firstNameIsDisplayed());
        SA.assertTrue(registerPage.lastNameIsDisplayed());
        SA.assertTrue(registerPage.genderMaleIsDisplayed());
        SA.assertTrue(registerPage.genderFemaleIsDisplayed());
        SA.assertTrue(registerPage.emailIsDisplayed());
        SA.assertTrue(registerPage.companyIsDisplayed());
        SA.assertTrue(registerPage.newsletterIsDisplayed());
        SA.assertTrue(registerPage.passwordIsDisplayed());
        SA.assertTrue(registerPage.confirmPasswordIsDisplayed());
        SA.assertTrue(registerPage.btnRegisterIsDisplayed());
        SA.assertTrue(registerPage.dropDayBirthIsDisplayed());
        SA.assertTrue(registerPage.dropMonthBirthIsDisplayed());
        SA.assertTrue(registerPage.dropYearBirthIsDisplayed());

        registerResultPage = registerPage.registerOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                 email,company,newsletter,password,confirmpassword);

        SA.assertTrue((registerResultPage.txtOKRegisterIsDisplayed()));

    }

    @Test
    public void testSearchProduct(){

        loginPage = homePage.clickInLogIn();


    }


/*


    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testRegisterNotOK(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword) throws InterruptedException {

        registerPage = homePage.clickInRegister();

        SA.assertTrue(registerPage.firstNameIsDisplayed());
        SA.assertTrue(registerPage.lastNameIsDisplayed());
        SA.assertTrue(registerPage.genderMaleIsDisplayed());
        SA.assertTrue(registerPage.genderFemaleIsDisplayed());
        SA.assertTrue(registerPage.emailIsDisplayed());
        SA.assertTrue(registerPage.companyIsDisplayed());
        SA.assertTrue(registerPage.newsletterIsDisplayed());
        SA.assertTrue(registerPage.passwordIsDisplayed());
        SA.assertTrue(registerPage.confirmPasswordIsDisplayed());
        SA.assertTrue(registerPage.btnRegisterIsDisplayed());
        SA.assertTrue(registerPage.dropDayBirthIsDisplayed());
        SA.assertTrue(registerPage.dropMonthBirthIsDisplayed());
        SA.assertTrue(registerPage.dropYearBirthIsDisplayed());

        registerPage.registerNotOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                email,company,newsletter,password,confirmpassword);

        SA.assertTrue((registerPage.textDisplayedAfterloginNotSuccess());




    }

 */






    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
        SA.assertAll();
    }












}// Fin clase
