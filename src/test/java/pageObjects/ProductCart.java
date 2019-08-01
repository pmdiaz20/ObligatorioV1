package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductCart extends BasePage  {


    List<ProductCartList> productos;

    public ProductCart(WebDriver driver)  {
        super(driver);
        productos = new ArrayList<>();
        WebElement tabla = driver.findElement(By.cssSelector("div.table-wrapper table tbody"));
        List<WebElement> filas = tabla.findElements(By.cssSelector("tr"));
        for(WebElement fila : filas){
            productos.add(new ProductCartList(fila));
        }
    }

    public boolean verificarsSiEstaProducto(String producto) {
        for(ProductCartList product : productos){
            if(product.getName().contains(producto))
                return true;
        }
        return false;
    }

    public boolean hayProductos() {
        return productos.size() > 0;
    }
}
