package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends BasePage {

    List<WishListProduct> productos;

    public WishListPage(WebDriver driver) {
        super(driver);
        productos = new ArrayList<>();
        List<WebElement> filas = driver.findElements(By.cssSelector("tbody tr"));
        for(WebElement fila : filas){
            productos.add(new WishListProduct(fila));
        }
    }

    public boolean chequearSiEstaProductoEnWishlist(String nombreProducto) {
        for(WishListProduct p : productos){
            if(p.getName().equals(nombreProducto))
                return true;
        }
        return false;
    }

    public Boolean chequearSiEstoyEnWishListPage(){
        return driver.findElement(By.xpath("//h1[contains(text(),'Wishlist')]")).isDisplayed();
    }




}
