package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePasswordPage extends BasePage {


    @FindBy(how = How.XPATH, using ="//h1[contains(text(),'My account - Change password')]")
    WebElement headerChangePassword;

    @FindBy(how = How.ID, using ="OldPassword")
    WebElement inputOldPassword;

    @FindBy(how = How.ID, using ="NewPassword")

    WebElement inputNewPassword;

    @FindBy(how = How.ID, using ="ConfirmNewPassword")
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

    public Boolean inputOldPasswordIsDisplayed()
    {
        return driver.findElement(By.id("OldPassword")).isDisplayed();
    }

    public Boolean inputNewPasswordIsDisplayed() {

        return driver.findElement(By.id("NewPassword")).isDisplayed();
    }

    public Boolean inputConfirmNewPasswordIsDisplayed() {

        return driver.findElement(By.id("ConfirmNewPassword")).isDisplayed();

    }

    public Boolean btnChangePasswordIsDisplayed(){
        return driver.findElement(By.xpath("//input[@class='button-1 change-password-button']")).isDisplayed();
    }


    public Boolean txtPasswordChangedOkIsDisplayed(){

        return driver.findElement(By.xpath("//*[contains(text(),'Password was changed')]")).isDisplayed();
    }

    public Boolean estoyEnChangePasswordPage(){
        return driver.findElement(By.xpath("//h1[contains(text(),'My account - Change password')]")).isDisplayed();
    }

    public Boolean newPassIgualOldPass()
    {
        return  driver.findElement(By.xpath("//li[contains(text(),'You entered the password that is the same as one o')]")).isDisplayed();
    }

    public Boolean newPassDistinctConfirmPass(){

        return driver.findElement(By.id("ConfirmNewPassword-error")).isDisplayed();

    }




}
