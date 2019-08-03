package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutCompletePage extends BasePage {


    @FindBy(how = How.XPATH, using ="//input[@class='button-1 order-completed-continue-button']")
    WebElement btnConfirmOrder;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public void finalizeOrder(){

        btnConfirmOrder.click();

    }


    public Boolean txtOrderOkIsDisplayed(){

        return driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")).isDisplayed();
    }

    public Boolean btnFinalizerOrderIsDisplayed()
    {
        return driver.findElement(By.xpath("//input[@class='button-1 order-completed-continue-button']")).isDisplayed();
    }

    public String txtOrderNumber(){

        String orden = driver.findElement(By.xpath("//*[@class='order-number']")).getText();
        System.out.println(orden);
        return orden;
    }



}
