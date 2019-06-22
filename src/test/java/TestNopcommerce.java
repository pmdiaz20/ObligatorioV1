import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
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
    //private WebDriverWait wait;
   // private LoginPage loginPage;
   // private AppointmentPage appointmentPage;
    //FinalPage finalpage;


    @BeforeMethod(alwaysRun = true)
    public void setupTest(){

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testRegister(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword) throws InterruptedException{

        registerPage = homePage.clickInRegister();
        Assert.assertTrue(registerPage.firstNameIsDisplayed());
        Assert.assertTrue(registerPage.lastNameIsDisplayed());
        Assert.assertTrue(registerPage.genderMaleIsDisplayed());
        Assert.assertTrue(registerPage.genderFemaleIsDisplayed());
        Assert.assertTrue(registerPage.emailIsDisplayed());
        Assert.assertTrue(registerPage.companyIsDisplayed());
        Assert.assertTrue(registerPage.newsletterIsDisplayed());
        Assert.assertTrue(registerPage.passwordIsDisplayed());
        Assert.assertTrue(registerPage.confirmPasswordIsDisplayed());
        Assert.assertTrue(registerPage.btnRegisterIsDisplayed());
        Assert.assertTrue(registerPage.dropDayBirthIsDisplayed());
        Assert.assertTrue(registerPage.dropMonthBirthIsDisplayed());
        Assert.assertTrue(registerPage.dropYearBirthIsDisplayed());

        registerResultPage = registerPage.registerOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                 email,company,newsletter,password,confirmpassword);

    }

    /*
    @Test
    public void testLogin(){

        loginPage = menuPage.clickAppointment();
        appointmentPage = loginPage.loginSuccess("John Doe","ThisIsNotAPassword");
        Assert.assertTrue(appointmentPage.textDisplayedAfterLogin()); //chequeo que se muestra el texto luego del login
    }
     */

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
