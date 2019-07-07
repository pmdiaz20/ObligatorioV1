import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;



public class TestNopcommerce {

    private WebDriver driver;
    private HomePage homePage;
    private NavigateBar navigateBar;
    private RegisterPage registerPage;
    private RegisterResultPage registerResultPage;
    private LoginPage loginPage;
    private ResultsPage resultsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;


    SoftAssert SA;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupTest(String browser){

        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("disable-infobars");
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver(opt);
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get("http://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        SA = new SoftAssert();

    }

    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testCP1RegistrarUsuario(String gender,String firstname,String lastname,
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


    @Test(dataProvider = "UserOkWithProduct",dataProviderClass = DataProviderClass.class)
    public void testCP2CheckoutConEfectivo(String valid_user,String valid_password,String productToSearch,
                                           String pais,String ciudad,String direccion1,String direccion2,
                                           String codigoPostal,String telefono,String fax){

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        resultsPage= homePage.searchProduct(productToSearch);
        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());
        resultsPage.addProductToShoppingCart();
        shoppingCartPage = homePage.clickInShoppingCart();
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart());
        checkoutPage= shoppingCartPage.clickInCheckout();
        checkoutPage.completar(pais,ciudad,direccion1,direccion2,codigoPostal,telefono,fax);




    }



    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
        SA.assertAll();
    }


}// Fin clase
