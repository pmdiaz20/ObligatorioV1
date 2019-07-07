package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShoppingCartPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@id='checkout']")
    WebElement btnCheckout;

    @FindBy(how = How.XPATH, using = "//input[@id='termsofservice']")
    WebElement checkAgree;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickInCheckout(){

        checkAgree.click();
        btnCheckout.click();

        return  new CheckoutPage(driver);
    }


    public boolean productIsInShoppingCart(){

        return driver.findElement(By.xpath("//a[@class='product-name']")).isDisplayed();
    }

}
