package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(how = How.ID, using = "checkout")
    WebElement btnCheckout;

    @FindBy(how = How.ID, using = "termsofservice")
    WebElement checkAgree;

    @FindBy(how = How.NAME, using = "updatecart")
    WebElement btnUpdateCart;

    List<ProductItem> productos;
    boolean encontrado;

    WebElement element;

    public ShoppingCartPage(WebDriver driver, WebElement element){
        super(driver);
        this.element = element;
    }


    public ShoppingCartPage(WebDriver driver) {
        super(driver);

    } //comentado 1/8

    public CheckoutPage clickInCheckout(){

        checkAgree.click();
        btnCheckout.click();

        return  new CheckoutPage(driver);
    }


    public boolean productIsInShoppingCart(String nombreProducto){
        return driver.findElement(By.className("product-name")).getText().contains(nombreProducto);
    }



    public String getName(){
        return element.findElement(By.cssSelector("td:nth-child(4)")).getText();
    }

    public void delete(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='remove-from-cart']//input[@type='checkbox']")));

        driver.findElement(By.xpath("//*[@class='remove-from-cart']//input[@type='checkbox']")).click();
        btnUpdateCart.click();
    }

    public Boolean btnUpdateCartIsDisplayed(){
        return driver.findElement(By.name("updatecart")).isDisplayed();
    }


    public Boolean emptyCart(){
        return driver.findElement(By.xpath("//div[@class='no-data']")).isDisplayed();
    }

    public Boolean btnCheckoutIsDisplayed(){
        return driver.findElement(By.id("checkout")).isDisplayed();
    }

    public Boolean checkBoxDeleteIsDisplayed(){
        return driver.findElement(By.xpath("//*[@class='remove-from-cart']//input[@type='checkbox']")).isDisplayed();
    }

}