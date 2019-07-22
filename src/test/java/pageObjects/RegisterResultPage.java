package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterResultPage extends BasePage {

    @FindBy(how = How.CLASS_NAME , using = "result")
    WebElement claseResultad;

    @FindBy(xpath = "//*[contains(text(),'Your registration completed')]")
    WebElement txtRegisterCompleted;

    public RegisterResultPage(WebDriver driver) {
        super(driver);

    }



    @FindBy(how = How.XPATH, using ="//input[@name='register-continue']")
    WebElement btnContinuarInRegisterPageOK;

    public boolean txtOKRegisterIsDisplayed(){return txtRegisterCompleted.isDisplayed();}

    public boolean btnContinuarIsDisplayed(){
        return  btnContinuarInRegisterPageOK.isDisplayed();
    }

    public boolean txtRegistoOkIsDisplayed(){

        return claseResultad.getText().contains("Your registration completed");
    }

    public void clickContinuarInRegisterPageOK()
    {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuarInRegisterPageOK));
        btnContinuarInRegisterPageOK.click();
    }
}
