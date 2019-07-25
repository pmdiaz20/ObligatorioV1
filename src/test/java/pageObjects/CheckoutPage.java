package pageObjects;

import org.openqa.selenium.*;
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

    @FindBy(how = How.XPATH,using = "//input[@class='button-1 order-completed-continue-button']")
    WebElement btnContinueInOrderCompleted;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAlertPresent(){
        boolean encuentraAlert = false;
      //  WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            encuentraAlert = true;
            System.out.println("isAlertPresent : " +encuentraAlert);
        } catch (TimeoutException eTO) {
            encuentraAlert = false;
            System.out.println("isAlertPresent : " +encuentraAlert);
        }
        return encuentraAlert;
    }

    public boolean aceptarAlerta()
    {   Alert alert;
        try
        {
            alert=driver.switchTo().alert();


        }
        catch(NoSuchElementException elementException)
        {
            return false;
        }

        alert.accept(); //Close Alert popup


        return true;
    }

    public CheckoutCompletePage realizarCheckoutConEfectivo(String pais, String ciudad, String direccion1, String direccion2, String codigoPostal, String telefono, String fax) {

        boolean primeraCompra = false;
        checkShipToSameAddress.click();

        try
        {
            driver.findElement(By.xpath("//select[@id='billing-address-select']")).isDisplayed();
        }
        catch(Exception e)
        {
            primeraCompra = true;
        }

        if (primeraCompra == false) {
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInBillingAddress));
            btnContinueInBillingAddress.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingAddress));
            btnContinueInShippingAddress.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingMethod));
            btnContinueInShippingMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentMethod));
            paymentMethod_Money.click();
            btnContinueInPaymentMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentInfo));
            btnContinueInPaymentInfo.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
            btnConfirmOrder.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));
        }
        else { // si entra acá es porque se la primera compra que hace el usuario y no hay otra direccion

            //1 Billing address
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

        }

        return new CheckoutCompletePage(driver);
    }


    public CheckoutCompletePage realizarCheckoutConTarjeta (String pais, String ciudad, String direccion1, String direccion2,
                         String codigoPostal, String telefono, String fax, String marcaTarjeta,
                         String titularTarjeta,String numeroTarjeta,String expiracionMes,String expriacionAnio,
                         String codigoSeguridad){

        boolean primeraCompra = false;
        wait.until(ExpectedConditions.elementToBeClickable(checkShipToSameAddress));
        checkShipToSameAddress.click();

        try
        {
            driver.findElement(By.xpath("//select[@id='billing-address-select']")).isDisplayed();
        }
        catch(Exception e)
        {
            primeraCompra = true;
        }

        if (primeraCompra == false) {
            System.out.println("no es primera compra");
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInBillingAddress));
            btnContinueInBillingAddress.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingAddress));
            btnContinueInShippingAddress.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingMethod));
            btnContinueInShippingMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentMethod));
            paymentMethod_CreditCard.click();
            btnContinueInPaymentMethod.click();
            wait.until(ExpectedConditions.elementToBeClickable(selectmarcaTarjetaInPaymentInformation));
            Select seleccionarmarcaDeTarjeta = new Select(selectmarcaTarjetaInPaymentInformation);
            seleccionarmarcaDeTarjeta.selectByValue(marcaTarjeta);
            inputCardHolderName.sendKeys(titularTarjeta);
            inputCardNumber.sendKeys(numeroTarjeta);
            Select seleccionarMesDeTarjeta = new Select(expirationMonth);
            seleccionarMesDeTarjeta.selectByVisibleText(expiracionMes);
            Select seleccionarAnioDeTarjeta = new Select(expirationYear);
            seleccionarAnioDeTarjeta.selectByVisibleText(expriacionAnio);
            inputCardCode.sendKeys(codigoSeguridad);
            btnContinueInPaymentInfo.click();


            wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
            btnConfirmOrder.click();

            if (isAlertPresent())
            {
                while (isAlertPresent())
                {
                    aceptarAlerta();
                    btnConfirmOrder.click();
                }
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 order-completed-continue-button']")));

        }
        else
        {
            System.out.println("es primera compra");

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

            btnContinueInBillingAddress.click();
            System.out.println("Completo Billing Address y paso a Shipping Address");

            // 2 Shipping address
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingAddress));
            btnContinueInShippingAddress.click();
            System.out.println("Completo Shipping Address y paso a Shipping Method");

            //3 Shipping method
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInShippingMethod));
            btnContinueInShippingMethod.click();
            System.out.println("Completo Shipping method y paso a Payment method");

            // 4 Payment method
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueInPaymentMethod));
            paymentMethod_CreditCard.click();
            btnContinueInPaymentMethod.click();
            System.out.println("Elijo CreditCard y continuo a Payment information");


            //5 Payment information

            wait.until(ExpectedConditions.elementToBeClickable(selectmarcaTarjetaInPaymentInformation));
            Select seleccionarmarcaDeTarjeta = new Select(selectmarcaTarjetaInPaymentInformation);
            seleccionarmarcaDeTarjeta.selectByValue(marcaTarjeta);

            inputCardHolderName.sendKeys(titularTarjeta);
            inputCardNumber.sendKeys(numeroTarjeta);

            Select seleccionarMesDeTarjeta = new Select(expirationMonth);
            seleccionarMesDeTarjeta.selectByVisibleText(expiracionMes);

            Select seleccionarAnioDeTarjeta = new Select(expirationYear);
            seleccionarAnioDeTarjeta.selectByVisibleText(expriacionAnio);

            inputCardCode.sendKeys(codigoSeguridad);
            btnContinueInPaymentInfo.click();

            // 6 Confirm order

            wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
            btnConfirmOrder.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 order-completed-continue-button']")));

            // www.guru99.com/alert-popup-handling-selenium.html
            //esperar que se muestre la alerta con un wait();
            //driver.switchTo().alert().dismiss();
            //driver.switchTo().alert().accept();

        }


        //btnContinueInOrderCompleted.click();

        return new CheckoutCompletePage(driver);

    }


}
