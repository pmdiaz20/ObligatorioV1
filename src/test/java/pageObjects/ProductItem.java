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
        //element.findElement(By.className("fa-shopping-cart")).click(); //modificado 31/7


        element.findElement(By.cssSelector("input[value='Add to cart']")).click();
    }

    public void addToCompare() {
        element.findElement(By.cssSelector("input[value='Add to compare list']")).click();
    }

    //    //span[@class='price actual-price']

    public String getPrice(){
        return element.findElement(By.className("prices")).getText();
    }
/*
    public double getValor() {
        String precio = element.findElement(By.className("prices")).getText().substring(1, element.findElement(By.className("prices")).getText().
        return Double.valueOf(precio);
    }*/

}
