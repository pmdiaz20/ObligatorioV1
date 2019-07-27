package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItem {

    WebElement element;

    public String getName(){
        return element.findElement(By.tagName("h2")).getText();
    }

    public ProductItem(WebElement main) {
        this.element = main;
    }

    public void addToWish() {
        element.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']")).click();
    }

    public void addToCart(){
        element.findElement(By.className("fa-shopping-cart")).click();
    }

    public void addToCompare() {
        element.findElement(By.cssSelector("input[value='Add to compare list']")).click();
    }
}
