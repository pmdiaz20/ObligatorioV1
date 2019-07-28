package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePasswordPage extends BasePage {


    @FindBy(how = How.XPATH, using ="//h1[contains(text(),'My account - Change password')]")
    WebElement headerChangePassword;

    @FindBy(how = How.XPATH, using ="//input[@id='OldPassword']")
    WebElement inputOldPassword;

    @FindBy(how = How.XPATH, using ="//input[@id='NewPassword']")

    WebElement inputNewPassword;

    @FindBy(how = How.XPATH, using ="//input[@id='ConfirmNewPassword']")
    WebElement inputConfirmNewPassword;

    @FindBy(how = How.XPATH, using ="//input[@class='button-1 change-password-button']")
    WebElement btnChangePassword;


    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }


    public void changePasswordOk(String oldPassword, String newPassword,String confirmNewPassword){
       // extentTest = extentReports.createTest(method.getName());

        inputOldPassword.sendKeys(oldPassword);
        inputNewPassword.sendKeys(newPassword);
        inputConfirmNewPassword.sendKeys(confirmNewPassword);

        btnChangePassword.click();
    }


    public Boolean txtPasswordChangedOkIsDisplayed(){

        return driver.findElement(By.xpath("//*[contains(text(),'Password was changed')]")).isDisplayed();
    }

    public Boolean newPassIgualOldPass()
    {
        return  driver.findElement(By.xpath("//li[contains(text(),'You entered the password that is the same as one o')]")).isDisplayed();
    }

    public Boolean newPassDistinctConfirmPass(){

        return driver.findElement(By.xpath("//span[@id='ConfirmNewPassword-error']")).isDisplayed();

    }




}
