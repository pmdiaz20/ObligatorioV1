package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateBar  {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@class= 'header-links']//*[text()='Register']")
    WebElement headerBtnRegister;

    @FindBy(how = How.XPATH, using = "//a[@class='ico-login']")
    WebElement headerBtnLogIn;

    @FindBy(how = How.XPATH, using ="//input[@id='small-searchterms']")
    WebElement searchBox;

    @FindBy(how = How.XPATH, using ="//input[@class='button-1 search-box-button']")
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using ="//span[@class='cart-label']")
    WebElement headerbtnShoppingCart;

    @FindBy(how = How.XPATH, using ="//span[@class='wishlist-label']")
    WebElement headerbtnWhisList;

    @FindBy(how = How.XPATH, using ="//a[@class='ico-account']")
    WebElement headerBtnMyAccount;

    @FindBy(how = How.XPATH, using ="//a[@class='ico-logout']")
    WebElement btnLogout;

    CurrencyPage currencyMenu;

    @FindBy(how = How.XPATH,using ="//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]")
    WebElement books;



    public NavigateBar(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
        currencyMenu = new CurrencyPage(driver);
    }

    public LoginPage clickInLogIn(){

        headerBtnLogIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));

        return new LoginPage(driver);
    }



    public RegisterPage clickInRegister(){

        headerBtnRegister.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new RegisterPage(driver);
    }

    public CustomerPage clickInMyAccount(){

        headerBtnMyAccount.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My account - Customer info')]")));
        return new CustomerPage(driver);
    }

    public ShoppingCartPage clickInShoppingCart(){

        // agrego javascript para scrollear hasta arriba

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        headerbtnShoppingCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Shopping cart')]")));
        wait.until(ExpectedConditions.elementToBeClickable(headerbtnShoppingCart));
        return new ShoppingCartPage(driver);

    }

    public ResultsPage searchProduct(String product){
        searchBox.sendKeys(product);
        btnSearch.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='product-title']//a[contains(text(),'"+product+"')]")));

        return new ResultsPage(driver);
    }

    public HomePage logout() {
       wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
        btnLogout.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ico-register']")));
        return new HomePage(driver);
    }

    public ResultsPage goToBooks(){
        books.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Books')]")));
        return new ResultsPage(driver);
    }

    public void seleccionarMoneda(String moneda){
        currencyMenu.seleccionarMoneda(moneda);
    }



}
