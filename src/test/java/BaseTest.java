import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import utils.GetProperties;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected NavigateBar navigateBar;
    protected RegisterPage registerPage;
    protected RegisterResultPage registerResultPage;
    protected LoginPage loginPage;
    protected ResultsPage resultsPage;
    protected ShoppingCartPage shoppingCartPage;
    protected CheckoutPage checkoutPage;
    protected CheckoutCompletePage checkoutCompletePage;
    protected SoftAssert SA;
    protected GetProperties properties = new GetProperties();
    protected String urlnopcommerce = properties.getString("URL");


    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupTest(String browser){

        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("disable-infobars");
            System.setProperty("webdriver.chrome.driver",properties.getString("CHROMEDRIVER_PATH"));
            driver = new ChromeDriver(opt);
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",properties.getString("FIREFOX_PATH"));
            driver = new FirefoxDriver();
        }

        driver.get(urlnopcommerce);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        SA = new SoftAssert();

    }


    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
        SA.assertAll();
    }

}
