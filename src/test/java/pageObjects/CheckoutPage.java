package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(how = How.XPATH, using ="//input[@id='ShipToSameAddress']")
    WebElement checkShipToSameAddress;

    @FindBy(how = How.XPATH, using ="//input[@id='BillingNewAddress_FirstName']")
    WebElement inputFirstNameInBillingAddress;

    @FindBy(how = How.XPATH, using ="//input[@id='BillingNewAddress_LastName']")
    WebElement inputLastNameInBillingAddress;

    @FindBy(how = How.XPATH, using ="//input[@id='BillingNewAddress_Email']")
    WebElement inputAddress_EmailInBillingAddress;

    @FindBy(how = How.XPATH, using ="//input[@id='BillingNewAddress_Company']")
    WebElement inputCompanyInBillingAddress;

    @FindBy(how = How.XPATH, using = "//select[@id='BillingNewAddress_CountryId']")
    WebElement selectCountryInBillingAddress;

    @FindBy(how = How.XPATH, using = "//select[@id='BillingNewAddress_StateProvinceId']")
    WebElement selectStateInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_City']")
    WebElement inputCityInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_Address1']")
    WebElement inputAddress1InBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_Address2']")
    WebElement inputAddress2InBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_ZipPostalCode']")
    WebElement inputPostalCodeInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_PhoneNumber']")
    WebElement inputPhoneNumberInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_FaxNumber']")
    WebElement inputFaxInBillingAddress;

    @FindBy(how = How.XPATH, using ="//div[@id='billing-buttons-container']//input[@class='button-1 new-address-next-step-button']")
    WebElement btnContinueInBillingAddress;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public void completar(String pais,String ciudad, String direccion1,String direccion2,String codigoPostal,String telefono,String fax){

        Select seleccionarCountry = new Select(selectCountryInBillingAddress);
        seleccionarCountry.selectByVisibleText(pais);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Other (Non US)')]")));

        inputCityInBillingAddress.sendKeys(ciudad);
        inputAddress1InBillingAddress.sendKeys(direccion1);
        inputAddress2InBillingAddress.sendKeys(direccion2);
        inputPostalCodeInBillingAddress.sendKeys(codigoPostal);
        inputPhoneNumberInBillingAddress.sendKeys(telefono);
        inputFaxInBillingAddress.sendKeys(fax);

        btnContinueInBillingAddress.click();

    }
}
