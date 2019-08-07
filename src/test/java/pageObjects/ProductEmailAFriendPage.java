package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductEmailAFriendPage extends BasePage {
    public ProductEmailAFriendPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "FriendEmail")
    WebElement inputFriendEmail;

    @FindBy(how = How.ID, using ="YourEmailAddress")
    WebElement inputMyEmail;

    @FindBy(how = How.ID, using = "PersonalMessage")
    WebElement boxMensajePersonal;

    @FindBy(how = How.NAME, using = "send-email")
    WebElement btnSendEmail;

    public void sendEmail(String productToSearchAndSend,String emailFriend){

        inputFriendEmail.click();
        inputFriendEmail.sendKeys(emailFriend);
        boxMensajePersonal.click();
        boxMensajePersonal.sendKeys("Please check this product:  "+productToSearchAndSend);
        btnSendEmail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your message has been sent')]"))).isDisplayed();

    }


    public Boolean msgSendOK()
    {
        return driver.findElement(By.xpath("//*[contains(text(),'Your message has been sent')]")).isDisplayed();
    }

    public Boolean estoyEnEmailAFriend(){
        return driver.findElement(By.xpath("//h1[contains(text(),'Email a friend')]")).isDisplayed();
    }

    public Boolean inputFriendEmailIsDisplayed()
    {
        return driver.findElement(By.xpath("//input[@id='FriendEmail']")).isDisplayed();
    }

    public Boolean inputMyEmailIsDisplayed(){
        return driver.findElement(By.xpath("//input[@id='YourEmailAddress']")).isDisplayed();
    }

    public Boolean boxMensajePersonalIsDisplayed(){
        return driver.findElement(By.xpath("//textarea[@id='PersonalMessage']")).isDisplayed();
    }

    public Boolean btnSendEmailIsDisplayed(){
        return driver.findElement(By.xpath("//input[@name='send-email']")).isDisplayed();
    }
}
