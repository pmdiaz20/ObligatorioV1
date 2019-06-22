package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterResultPage extends BasePage {

    @FindBy(how = How.CLASS_NAME , using = "result")
    WebElement claseResultad;

    public RegisterResultPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(how = How.CLASS_NAME, using = "button-1 register-continue-button")
    WebElement btnCotinuar;

    public boolean btnContinuarIsDisplayed(){
        return  btnCotinuar.isDisplayed();
    }

    public boolean txtRegistoOkIsDisplayed(){

        return claseResultad.getText().contains("Your registration completed");
    }
}
