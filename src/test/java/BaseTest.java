import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import sun.awt.OSInfo;
import utils.GetProperties;

import java.io.IOException;

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
    protected WishListPage wishListPage;
    protected CompareProductsPage compareProductsPage;
    protected CustomerPage customerPage;
    protected ChangePasswordPage changePasswordPage;


    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    @Parameters("browser")
    public void setupSuite(String browser){
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("disable-infobars");
            System.setProperty("webdriver.chrome.driver",
                    properties.getString("CHROMEDRIVER_PATH"));
            driver = new ChromeDriver(opt);
//            driver = new RemoteWebDriver(new URL(hubUrl), opt);
        } else if (browser.equalsIgnoreCase(
                "firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    properties.getString("FIREFOX_PATH"));
            driver = new FirefoxDriver();
//            FirefoxOptions fopts = new FirefoxOptions();
//            driver = new RemoteWebDriver(new URL(hubUrl), fopts);
        }

        SA = new SoftAssert();

        setupReports();
    }

    public void setupReports(){
        extentHtmlReporter = new ExtentHtmlReporter("reports/reporte.html");
        extentHtmlReporter.config().setDocumentTitle("Automation Reports");
        extentHtmlReporter.config().setReportName("Obligatorio Pablo Diaz - Reporte de Pruebas Automatizadas en demo.nopcommerce.com");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        extentReports.setSystemInfo("Ambiente", "Testing");
        extentReports.setSystemInfo("Hostname", "nopcommerce");
        extentReports.setSystemInfo("Sistema Operativo", OSInfo.getOSType().name());
        extentReports.setSystemInfo("Obligatorio", "Pablo Diaz");
    }


    @BeforeMethod(alwaysRun = true)
    public void navegarAPaginaInicial() {
        driver.get(urlnopcommerce);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }


    @AfterMethod(alwaysRun = true)
    public void teardownTest(ITestResult resultadoDeTest) throws IOException {

        SA.assertAll();

        if(resultadoDeTest.getStatus() == ITestResult.FAILURE)
        {
            extentTest.log(Status.FAIL, "Test Case " + resultadoDeTest.getName() + " failed");
            extentTest.log(Status.FAIL, "Caused: " + resultadoDeTest.getThrowable());
        }
        else if(resultadoDeTest.getStatus() == ITestResult.SKIP)
        {
            extentTest.log(Status.SKIP, "Test Case " + resultadoDeTest.getName() + " skipped");
            extentTest.log(Status.SKIP, "Caused: " + resultadoDeTest.getThrowable());
        }
        else if(resultadoDeTest.getStatus() == ITestResult.SUCCESS){
            extentTest.log(Status.PASS, "Test Case " + resultadoDeTest.getName() + " passed");
        }

    }


    @AfterSuite(alwaysRun = true)
    public void flush(){
        extentReports.flush();
        //driver.quit();
    }

}
