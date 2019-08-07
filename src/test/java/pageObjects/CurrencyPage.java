package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrencyPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using="customerCurrency")
     WebElement listaDeMonedas;

    public CurrencyPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void seleccionarMoneda(String moneda){

        wait.until(ExpectedConditions.elementToBeClickable(listaDeMonedas));
        listaDeMonedas.click();

        Select seleccionarMoneda = new Select(listaDeMonedas);
        seleccionarMoneda.selectByVisibleText(moneda);

        //System.out.println("seleccione moneda: "+moneda);
    }


}
