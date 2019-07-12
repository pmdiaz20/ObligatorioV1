package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage  {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using ="FirstName")
    WebElement nombre;

    @FindBy(how = How.ID, using = "LastName")
    WebElement apellido;

    @FindBy(how = How.ID, using ="Email")
    WebElement correo;

    @FindBy(how = How.ID, using ="Password")
    WebElement pass;

    @FindBy(how = How.ID, using = "ConfirmPassword")
    WebElement confirmPass;

    @FindBy(how = How.ID, using ="Company")
    WebElement compania;

    @FindBy(how = How.ID, using ="gender-male")
    WebElement generoMasculino;

    @FindBy(how = How.ID, using ="gender-female")
    WebElement generoFemenino;

    @FindBy(how = How.ID, using ="Newsletter")
    WebElement noticias;

    @FindBy(how = How.ID, using = "register-button")
    WebElement btnRegister;

    @FindBy(how = How.XPATH, using = "//*[@class='date-picker-wrapper']//*[@name='DateOfBirthDay']")
    WebElement diaDeCumple;

    @FindBy(how = How.XPATH, using = "//*[@class='date-picker-wrapper']//*[@name='DateOfBirthMonth']")
    WebElement mesDeCumple;

    @FindBy(how = How.XPATH, using = "//*[@class='date-picker-wrapper']//*[@name='DateOfBirthYear']")
    WebElement anioDeCumple;




    public RegisterResultPage registerOK(String gender, String firstname,String lastname,
                           String dayBirth,String monthBirth,String yearBirth,
                           String email,String company,String newsletter,
                           String password,String confirmpassword) {

        nombre.sendKeys(firstname);
        apellido.sendKeys(lastname);
        correo.sendKeys(email);
        pass.sendKeys(password);
        confirmPass.sendKeys(confirmpassword);
        compania.sendKeys(company);

        Select seleccionarDia = new Select(diaDeCumple);
        seleccionarDia.selectByVisibleText(dayBirth);

        Select seleccionarMes = new Select(mesDeCumple);
        seleccionarMes.selectByVisibleText(monthBirth);

        Select seleccionarAnio = new Select(anioDeCumple);
        seleccionarAnio.selectByVisibleText(yearBirth);

        if (newsletter.equals("YES"))
        {
            noticias.click();
        }

        if (gender.equals("M"))
        {
            generoMasculino.click();
         //   Assert.assertTrue(maleRadioBtn.isSelected());
        }
        else if (gender == "F")
        {
            generoFemenino.click();
           // Assert.assertTrue(femaleRadioBtn.isSelected());
        }

        btnRegister.click();

        // espero hasta que aparezca el texto Your registration completed


        //*[text()='Your registration completed']

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='result']")));


        return new RegisterResultPage(driver);
    }

    public boolean firstNameIsDisplayed(){return nombre.isDisplayed();}
    public boolean lastNameIsDisplayed(){return apellido.isDisplayed();}
    public boolean emailIsDisplayed(){return correo.isDisplayed();}
    public boolean passwordIsDisplayed(){return pass.isDisplayed();}
    public boolean confirmPasswordIsDisplayed(){return confirmPass.isDisplayed();}
    public boolean companyIsDisplayed(){return compania.isDisplayed();}
    public boolean genderMaleIsDisplayed(){return generoMasculino.isDisplayed();}
    public boolean genderFemaleIsDisplayed(){return generoFemenino.isDisplayed();}
    public boolean newsletterIsDisplayed(){return noticias.isDisplayed();}
    public boolean btnRegisterIsDisplayed(){return btnRegister.isDisplayed();}
    public boolean dropDayBirthIsDisplayed(){return diaDeCumple.isDisplayed();}
    public boolean dropMonthBirthIsDisplayed(){return mesDeCumple.isDisplayed();}
    public boolean dropYearBirthIsDisplayed(){return anioDeCumple.isDisplayed();}


/*
    public void registerNotOK(String gender, String firstname,String lastname,
                                         String dayBirth,String monthBirth,String yearBirth,
                                         String email,String company,String newsletter,
                                         String password,String confirmpassword) {
        nombre.sendKeys(firstname);
        apellido.sendKeys(lastname);
        correo.sendKeys(email);
        pass.sendKeys(password);
        confirmPass.sendKeys(confirmpassword);
        compania.sendKeys(company);

        Select seleccionarDia = new Select(diaDeCumple);
        seleccionarDia.selectByVisibleText(dayBirth);

        Select seleccionarMes = new Select(mesDeCumple);
        seleccionarMes.selectByVisibleText(monthBirth);

        Select seleccionarAnio = new Select(anioDeCumple);
        seleccionarAnio.selectByVisibleText(yearBirth);

        if (newsletter.equals("YES"))
        {
            noticias.click();
        }

        if (gender.equals("M"))
        {
            generoMasculino.click();
        }
        else if (gender == "F")
        {
            generoFemenino.click();
        }

        btnRegister.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='message-error validation-summary-errors']")));

       /* wait.until(ExpectedConditions.or(
                (ExpectedConditions.visibilityOfElementLocated(By.id("FirstName-error"))),
                (ExpectedConditions.visibilityOfElementLocated(By.id("LastName-error"))),
                (ExpectedConditions.visibilityOfElementLocated(By.id("Email-error"))),
                (ExpectedConditions.visibilityOfElementLocated(By.id("Password-error"))),
                (ExpectedConditions.visibilityOfElementLocated(By.id("ConfirmPassword-error")))
        ));

    } */





    /*

    //The specified email already exists
    public boolean textDisplayedAfterloginNotSuccess() {
        boolean resultado = false;
        if(driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/p[2]")).getText().contains("Login failed! Please ensure the username and password are valid."))
        {
            resultado=true;
        }

        return resultado;
    }





    public boolean textDisplayedAfterRegisterNotSuccess() {
        return driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/p[2]"))   .getText().contains("Login failed! Please ensure the username and password are valid.");
    }
 */


}// Fin de clase RegisterPage




