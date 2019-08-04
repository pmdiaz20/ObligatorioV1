package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected NavigateBar bar;

    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    protected static String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        PageFactory.initElements(driver, this);
        bar = new NavigateBar(driver);

    }


    public RegisterPage clickInRegister(){
        return bar.clickInRegister();
    }

    public CustomerPage clickInMyAccount(){
        return bar.clickInMyAccount();
    }

    public LoginPage clickInLogIn(){
        return bar.clickInLogIn();
    }

    public ShoppingCartPage clickInShoppingCart(){
        return bar.clickInShoppingCart();
    }

    public WishListPage clickInWishList(){
        return bar.clickInWishList();
    }

    public ResultsPage searchProduct(String productToSearch){
        return bar.searchProduct(productToSearch);
    }

    public HomePage clickInlogout() { return bar.logout();}

    public ResultsPage gotoBooks()
    {
        return bar.goToBooks();
    }

    public void seleccionarMoneda(String moneda){
        bar.seleccionarMoneda(moneda);
    }


}


