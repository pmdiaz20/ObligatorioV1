package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WishListProduct {

    WebElement element;

    public WishListProduct(WebElement element)
    {
        this.element = element;
    }

    public String getName()
    {
        return element.findElement(By.cssSelector("td:nth-child(5)")).getText();
    }

    public void addToCart()
    {
        element.findElement(By.name("addtocartbutton")).click();
    }

    public void updateWishList()
    {
        element.findElement(By.name("updatecart")).click();
    }

}