package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCartList {

    WebElement element;

    public ProductCartList(WebElement element){
        this.element = element;
    }

    public String getName(){
        return element.findElement(By.cssSelector("td:nth-child(4)")).getText();
    }

    public void delete(){
        element.findElement(By.xpath("//*[@class='remove-from-cart']//input[@type='checkbox']")).click();
    }
}
