package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 product-box-add-to-cart-button']")
    WebElement btnAddToCart;

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 add-to-wishlist-button']")
    WebElement btnAddToWishList;

    List<ProductItem> resultados;
    boolean encontrado;

    public ResultsPage(WebDriver driver) {
        super(driver);
        resultados = new ArrayList<>();
        encontrado = false;
        List<WebElement> listadoElementos = driver.findElements(By.xpath(" //div[@class='item-grid']"));
        for(WebElement element : listadoElementos){
            resultados.add(new ProductItem(element));
        }
    }

    public void addProductToShoppingCart(){
        btnAddToCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='cart-label']"))));
    }

    public Boolean btnAddToCartIsDisplayed(){

        return driver.findElement(By.xpath("//input[@class='button-2 product-box-add-to-cart-button']")).isDisplayed();
    }

    public void addToWishList(String object){
        for (ProductItem product : resultados){
            if (product.getName().equals(object)){
                product.addToWish();
                encontrado = true;
                break;
            }
        }
    }

/*
    public void addToCart(String object){
        for(ProductItem product : resultados){
            if(product.getName().equals(object)){
                product.addToCart();
                break;
            }
        }
    }*/

}
