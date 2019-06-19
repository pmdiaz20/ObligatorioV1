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

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")
    WebElement btnRegister;

    /*
    @FindBy(how = How.ID,using = "btn-make-appointment")
    WebElement botonMakeAppointment;

    public LoginPage clickAppointment(){
        botonMakeAppointment.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        return new LoginPage(driver);
    }
     */

    public RegisterPage clickInRegister(){

        btnRegister.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new RegisterPage(driver);
    }
}
