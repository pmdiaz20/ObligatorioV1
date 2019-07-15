package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@id='ShipToSameAddress']")
    WebElement checkShipToSameAddress;

    @FindBy(how = How.XPATH, using = "//select[@id='billing-address-select']")
    WebElement selectAddress;

    @FindBy(how = How.XPATH, using = "//select[@id='BillingNewAddress_StateProvinceId']")
    WebElement selectStateInBillingAddress;


    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_FirstName']")
    WebElement inputFirstNameInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_LastName']")
    WebElement inputLastNameInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_Email']")
    WebElement inputAddress_EmailInBillingAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='BillingNewAddress_Company']")
    WebElement inputCompanyInBillingAddress;

    @FindBy(how = How.XPATH, using = "//select[@id='BillingNewAddress_CountryId']")
    WebElement selectCountryInBillingAddress;

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

    @FindBy(how = How.XPATH, using = "//div[@id='billing-buttons-container']//input[@class='button-1 new-address-next-step-button']")
    WebElement btnContinueInBillingAddress;

    @FindBy(how = How.XPATH, using = "//div[@id='shipping-buttons-container']//input[@class='button-1 new-address-next-step-button']")
    WebElement  btnContinueInShippingAddress;

    @FindBy(how = How.XPATH, using = "//input[@class='button-1 shipping-method-next-step-button']")
    WebElement btnContinueInShippingMethod;

    @FindBy(how = How.XPATH, using = "//input[@id='paymentmethod_0']")
    WebElement paymentMethod_Money;

    @FindBy(how = How.XPATH, using = "//input[@id='paymentmethod_1']")
    WebElement paymentMethod_CreditCard;


    @FindBy(how = How.XPATH, using = "//input[@class='button-1 payment-method-next-step-button']")
    WebElement btnContinueInPaymentMethod;


    @FindBy(how = How.XPATH, using = "//input[@class='button-1 payment-info-next-step-button']")
    WebElement btnContinueInPaymentInfo;

    @FindBy(how = How.XPATH, using = "//*[@class='dropdownlists']")
    WebElement selectmarcaTarjetaInPaymentInformation;

    @FindBy(how = How.XPATH, using ="//input[@id='CardholderName']")
    WebElement inputCardHolderName;

    @FindBy(how = How.XPATH, using ="//input[@id='CardNumber']")
    WebElement inputCardNumber;

    @FindBy(how = How.XPATH, using ="//select[@id='ExpireMonth']")
    WebElement expirationMonth;

    @FindBy(how = How.XPATH, using ="//select[@id='ExpireYear']")
    WebElement expirationYear;

    @FindBy(how = How.XPATH, using = "//input[@id='CardCode']")
    WebElement inputCardCode;


    @FindBy(how = How.XPATH, using = "//input[@class='button-1 confirm-order-next-step-button']")
    WebElement btnConfirmOrder;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public CheckoutCompletePage realizarCheckoutConEfectivo(String pais, String ciudad, String direccion1, String direccion2, String codigoPostal, String telefono, String fax) {




        //1 Billing address
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInBillingAddress));
        Select seleccionarCountry = new Select(selectCountryInBillingAddress);
        seleccionarCountry.selectByVisibleText(pais);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Other (Non US)')]")));
        inputCityInBillingAddress.sendKeys(ciudad);
        inputAddress1InBillingAddress.sendKeys(direccion1);
        inputAddress2InBillingAddress.sendKeys(direccion2);
        inputPostalCodeInBillingAddress.sendKeys(codigoPostal);
        inputPhoneNumberInBillingAddress.sendKeys(telefono);
        inputFaxInBillingAddress.sendKeys(fax);
        checkShipToSameAddress.click();
        btnContinueInBillingAddress.click();

        // 2 Shipping address
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingAddress));
        btnContinueInShippingAddress.click();

        //3 Shipping method
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingMethod));
        btnContinueInShippingMethod.click();

        // 4 Payment method
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentMethod));
        paymentMethod_Money.click();
        btnContinueInPaymentMethod.click();

        // 5 Payment information
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentInfo));
        btnContinueInPaymentInfo.click();

        // 6 Confirm order
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
        btnConfirmOrder.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));

        return new CheckoutCompletePage(driver);
    }


    public CheckoutCompletePage realizarCheckoutConTarjeta (String pais, String ciudad, String direccion1, String direccion2,
                         String codigoPostal, String telefono, String fax, String marcaTarjeta,
                         String titularTarjeta,String numeroTarjeta,String expiracionMes,String expriacionAnio,
                         String codigoSeguridad){

    //1 Billing address
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInBillingAddress));
        Select seleccionarCountry = new Select(selectCountryInBillingAddress);
        seleccionarCountry.selectByVisibleText(pais);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Other (Non US)')]")));
        inputCityInBillingAddress.sendKeys(ciudad);
        inputAddress1InBillingAddress.sendKeys(direccion1);
        inputAddress2InBillingAddress.sendKeys(direccion2);
        inputPostalCodeInBillingAddress.sendKeys(codigoPostal);
        inputPhoneNumberInBillingAddress.sendKeys(telefono);
        inputFaxInBillingAddress.sendKeys(fax);
        checkShipToSameAddress.click();
        btnContinueInBillingAddress.click();

        // 2 Shipping address
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingAddress));
        btnContinueInShippingAddress.click();

        //3 Shipping method
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingMethod));
        btnContinueInShippingMethod.click();

        // 4 Payment method
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentMethod));
        paymentMethod_CreditCard.click();
        btnContinueInPaymentMethod.click();

        //5 Payment information

        Select seleccionarmarcaDeTarjeta = new Select(selectmarcaTarjetaInPaymentInformation);
        seleccionarmarcaDeTarjeta.selectByValue(marcaTarjeta);

        inputCardHolderName.sendKeys(titularTarjeta);
        inputCardNumber.sendKeys(numeroTarjeta);

        Select seleccionarMesDeTarjeta = new Select(expirationMonth);
        seleccionarMesDeTarjeta.selectByVisibleText(expiracionMes);

        Select seleccionarAnioDeTarjeta = new Select(expirationYear);
        seleccionarAnioDeTarjeta.selectByVisibleText(expriacionAnio);

        inputCardCode.sendKeys(codigoSeguridad);

        // 6 Confirm order

        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
        btnConfirmOrder.click();

        return new CheckoutCompletePage(driver);

    }


}
