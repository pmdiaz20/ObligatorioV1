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

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Compare products list')]")
    WebElement compareListLinkText;

    public CompareProductsPage clickInCompareList()
    {
        compareListLinkText.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Compare products')]")));

        return new CompareProductsPage(driver);
    }

    public ResultsPage(WebDriver driver) {
        super(driver);
        resultados = new ArrayList<>();
        encontrado = false;
        List<WebElement> listadoElementos = driver.findElements(By.xpath("//div[@class='item-box']"));
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

    public void addToCompareList(String producto){
        int contador=1;
        for (ProductItem product : resultados){

            System.out.println("tamanio de resultados: "+resultados.size());
            System.out.println(product.getName());
            product.addToCompare();
            System.out.println("Se agrega "+product.getName());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));
            driver.findElement(By.xpath("//span[@class='close']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[@class='content']")));


            /*
            if(product.getName().contains(producto))
            {
                if (contador < 3)
                {
                    product.addToCompare();
                    contador = (contador + 1 );
                }
                else
                {
                    break;
                }

            }
            // encontrado = true;
*/

        }
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

    public boolean productAddedToWishList(String product){
        if(!encontrado) {
            System.err.println("producto " + product + " no encontrado");
            return false;
        }
        else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//*[contains(text(),'The product has been added to your ')]"))));
            WebElement addToWishListSucces = driver.findElement(By.xpath(("//*[contains(text(),'The product has been added to your ')]")));
            return addToWishListSucces.getText().contains("wishlist");
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