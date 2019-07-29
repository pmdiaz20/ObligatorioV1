package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddressesPage extends CustomerPage {

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 edit-address-button']")
    WebElement btnEditAddress;

    @FindBy(how = How.XPATH, using = "//input[@class='button-2 delete-address-button']")
    WebElement btnDeleteAdress;

    @FindBy(how = How.XPATH, using = "//input[@class='button-1 add-address-button']")
    WebElement btnAddNewAddress;

    public Boolean noExistsAddreess(){
        return driver.findElement(By.xpath("//div[@class='no-data']")).isDisplayed();
    }

    public AddressesPage(WebDriver driver) {
        super(driver);
    }
}
