package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 product-box-add-to-cart-button']")
    WebElement btnAddToCart;

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 add-to-wishlist-button']")
    WebElement btnAddToWishList;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToShoppingCart(){
        btnAddToCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='cart-label']"))));
    }

    public Boolean btnAddToCartIsDisplayed(){

        return driver.findElement(By.xpath("//input[@class='button-2 product-box-add-to-cart-button']")).isDisplayed();
    }

}
