package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class InfoProductPage extends BasePage {


    @FindBy(how = How.CLASS_NAME, using ="email-a-friend")
    WebElement btnSendEmailToFriend;


    public InfoProductPage(WebDriver driver) {
        super(driver);
    }



    public ProductEmailAFriendPage sendProduct(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSendEmailToFriend));
        btnSendEmailToFriend.click();

        return new ProductEmailAFriendPage(driver);
    }

}
