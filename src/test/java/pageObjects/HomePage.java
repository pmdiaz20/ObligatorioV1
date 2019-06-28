package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //"/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")
    @FindBy(how = How.XPATH, using = "//*[@class= 'header-links']//*[text()='Register']")
    WebElement headerBtnRegister;

    @FindBy(how = How.XPATH, using = "//*[@class= 'header-links']//*[text()='Log in']")
    WebElement headerBtnLogIn;


    // "//*[@class= 'header-links']//*[text()='Log out']")
    @FindBy(how = How.XPATH, using = "//a[@href='/logout']")
    WebElement headerBtnLogOut;

    @FindBy(how= How.ID, using = "Email")
    WebElement email;

    @FindBy(how= How.ID, using = "Password")
    WebElement pass;

    @FindBy(how = How.XPATH, using = "//*[@class='buttons']//*[@type='submit' and @value='Log in']")
    WebElement btnlogin;

    public RegisterPage clickInRegister(){

        headerBtnRegister.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new RegisterPage(driver);
    }

    public LoginPage clickInLogIn(){
        headerBtnLogIn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        return new LoginPage(driver);
    }

    public HomePage loguearse(String valid_email, String valid_pass){

        email.sendKeys(valid_email);
        pass.sendKeys(valid_pass);
        btnlogin.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));

        return new HomePage(driver);
    }





}
