package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "Email")
    WebElement inputEmail;

    @FindBy(how = How.ID, using = "Password")
    WebElement inputPassword;

    @FindBy(how = How.XPATH, using = "//input[@class='button-1 login-button']")
    WebElement btnLogIn;


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public HomePage login(String valid_user,String valid_password){

        inputEmail.sendKeys(valid_user);
        inputPassword.sendKeys(valid_password);
        btnLogIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Log out")));

        return new HomePage(driver);
    }

}
