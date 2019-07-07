package pageObjects;

import org.openqa.selenium.By;
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

    //@FindBy(how = How.XPATH, using = "//*[@class= 'header-links']//*[text()='Log in']")
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


    public NavigateBar(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickInLogIn(){

        headerBtnLogIn.click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));

        return new LoginPage(driver);
    }

    public RegisterPage clickInRegister(){

        headerBtnRegister.click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new RegisterPage(driver);
    }

    public ShoppingCartPage clickInShoppingCart(){

        headerbtnShoppingCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Shopping cart')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-name']")));
        return new ShoppingCartPage(driver);

    }

    public ResultsPage searchProduct(String product){
        searchBox.sendKeys(product);
        btnSearch.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='product-title']//a[contains(text(),'"+product+"')]")));

        return new ResultsPage(driver);
    }
}
