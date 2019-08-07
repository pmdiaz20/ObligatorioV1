package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 product-box-add-to-cart-button']")
    WebElement btnAddToCart;

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 add-to-wishlist-button']")
    WebElement btnAddToWishList;

    List<ProductItem> resultados;
    boolean encontrado;

    ArrayList<String> nombreProducto;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Compare products list')]")
    WebElement compareListLinkText;


    @FindBy(how = How.XPATH, using ="//select[@id='products-orderby']")
    WebElement selectSortBy;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));
        driver.findElement(By.xpath("//span[@class='close']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='cart-label']"))));
    }

    public Boolean btnAddToCartIsDisplayed(){

        return driver.findElement(By.xpath("//input[@class='button-2 product-box-add-to-cart-button']")).isDisplayed();
    }

    public Boolean btnAddToWishListIsDisplayed() {

        return driver.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']")).isDisplayed();
    }

    public Boolean btnAddToCompareIsDisplayed(){
        return driver.findElement(By.cssSelector("input[value='Add to compare list']")).isDisplayed();
    }

    public void addToCompareList(String producto){
        for (ProductItem product : resultados){
          //  System.out.println("tamanio de resultados: "+resultados.size());
          //  System.out.println(product.getName());
            product.addToCompare();
          //  System.out.println("Se agrega "+product.getName());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));
            driver.findElement(By.xpath("//span[@class='close']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[@class='content']")));
        }
    }

    public Integer cantidadDeProductos(){
        return resultados.size();
    }

    public Boolean busquedaDevuelveMasdeUnProducto(){
        return resultados.size() > 1;
    }

    public Boolean compareListLinkExist()
    {
        return driver.findElement(By.xpath("//a[contains(text(),'Compare products list')]")).isDisplayed();
    }


    public ArrayList<String> nombreDeProductosEncontrados(){
        nombreProducto= new ArrayList<String>();
        String nombre="";
        for(int i=0; i< resultados.size();i++)
        {
            nombre= resultados.get(i).getName();
            nombreProducto.add(nombre);
        }

        return  nombreProducto;

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

    public InfoProductPage verDetalle(String object){
        for (ProductItem product : resultados){
            if (product.getName().equals(object)){

                driver.findElement(By.xpath("//div[@class='picture']//a//img")).click();


            }

        }
        return new InfoProductPage(driver);
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

    public void sortByPriceLowToHigh()
    {
        Select ordernarPor = new Select(selectSortBy);
        ordernarPor.selectByVisibleText("Price: Low to High");

    }

    public void agregarAlCarritoMenorPrecio() {
        int indice = -1;
        double menorPrecio = 999999.00;
        for(int i = 0; i < resultados.size(); i++){

            resultados.get(0).addToCart();
            break;
        }
     //   setProductName(resultados.get(indice).getName());
       // resultados.get(indice).addToCart();
    }

    public Boolean productoLocalizado (String product){
        return driver.findElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'"+product+"')]")).isDisplayed();
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