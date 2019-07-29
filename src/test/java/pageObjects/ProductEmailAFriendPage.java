package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductEmailAFriendPage extends BasePage {
    public ProductEmailAFriendPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//input[@id='FriendEmail']")
    WebElement inputFriendEmail;

    @FindBy(how = How.XPATH, using = "//textarea[@id='PersonalMessage']")
    WebElement boxMensajePersonal;

    @FindBy(how = How.XPATH, using = "//input[@name='send-email']")
    WebElement btnSendEmail;

    public void sendEmail(String productToSearchAndSend,String emailFriend){

        inputFriendEmail.click();
        inputFriendEmail.sendKeys(emailFriend);
        boxMensajePersonal.click();
        boxMensajePersonal.sendKeys("Please check this product:  "+productToSearchAndSend);
        btnSendEmail.click();

    }


    public Boolean msgSendOK()
    {
        return driver.findElement(By.xpath("//*[contains(text(),'Your message has been sent')]")).isDisplayed();
    }
}
