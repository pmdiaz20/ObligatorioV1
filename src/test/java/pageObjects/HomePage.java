package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }



    public String simboloDeMoneda(String moneda){
        String monedaSimbolo="";

        if(moneda.equalsIgnoreCase("Euro"))
        {
            monedaSimbolo="Ђ";
        }
        else if(moneda.equalsIgnoreCase("US Dollar"))
        {
            monedaSimbolo="$";
        }

        return monedaSimbolo;
    }


    public boolean checkChangeCurrency(String moneda){
        String simbolo = simboloDeMoneda(moneda);
        boolean monedaOK = true;
        List<WebElement> listadoElementos = driver.findElements(By.className("product-item"));
        List<ProductItem> listadoProductos = new ArrayList<>();
        for(WebElement element : listadoElementos){
            listadoProductos.add(new ProductItem(element));
        }
        for (ProductItem productItem : listadoProductos){
       //     System.out.println("el precio del producto es: "+ productItem.getPrice());
            if(!productItem.getPrice().contains(simbolo))
                monedaOK = false;
        }
        return monedaOK;
    }



}
