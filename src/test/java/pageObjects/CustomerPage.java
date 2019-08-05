package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CustomerPage extends BasePage {
    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using ="//a[@class='inactive'][contains(text(),'Orders')]")
    WebElement btnOrders;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Change password')]")
    WebElement btnChangePassword;

    public ChangePasswordPage goToChangePassword(){
        btnChangePassword.click();
        return new ChangePasswordPage(driver);
    }

    public Boolean changePasswordIsDisplayed(){
        return driver.findElement(By.xpath("//a[contains(text(),'Change password')]")).isDisplayed();
    }
}
