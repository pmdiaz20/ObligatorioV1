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
    WebElement btnRegister;

    @FindBy(how = How.XPATH, using = "//*[@class= 'header-links']//*[text()='Log in']")
    WebElement btnLogIn;

    public RegisterPage clickInRegister(){

        btnRegister.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new RegisterPage(driver);
    }

    public LoginPage clickInLogIn(){
        btnLogIn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        return new LoginPage(driver);
    }





}
