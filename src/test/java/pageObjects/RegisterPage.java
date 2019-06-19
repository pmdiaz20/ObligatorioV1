package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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

    //Datos opcionales //Gender ,Date of birth 3 selects,Company name,Newsletter

    @FindBy(how = How.ID, using ="Company")
    WebElement compania;

    @FindBy(how = How.ID, using ="gender-male")
    WebElement generoMasculino;

    @FindBy(how = How.ID, using ="gender-female")
    WebElement generoFemenino;

    @FindBy(how = How.ID, using ="Newsletter")
    WebElement noticias;

    /*


WebElement newsletter = driver.findElement(By.id("Newsletter"));
        newsletter.click();

        WebElement btnRegister = driver.findElement(By.id("register-button"));
        btnRegister.click();


        Assert.assertTrue(inputCompany.isDisplayed());
        Assert.assertTrue(inputCompany.isEnabled());


    WebElement maleRadioBtn = driver.findElement(By.id("gender-male"));
        Assert.assertTrue(maleRadioBtn.isDisplayed());
        Assert.assertTrue(maleRadioBtn.isEnabled());
    WebElement femaleRadioBtn = driver.findElement(By.id("gender-female"));
        Assert.assertTrue(femaleRadioBtn.isDisplayed());
        Assert.assertTrue(femaleRadioBtn.isEnabled()); */
/*
//Datos obligatorios

        Assert.assertTrue(inputFirstName.isDisplayed());
        Assert.assertTrue(inputFirstName.isEnabled());
        Assert.assertTrue(inputLastName.isDisplayed());
        Assert.assertTrue(inputLastName.isEnabled());
        Assert.assertTrue(inputEmail.isDisplayed());
        Assert.assertTrue(inputEmail.isEnabled());
        Assert.assertTrue(inputPassword.isDisplayed());
        Assert.assertTrue(inputPassword.isEnabled());
        Assert.assertTrue(inputConfirmPassword.isDisplayed());
        Assert.assertTrue(inputConfirmPassword.isEnabled());

 */

    public void registerOK(String firstname,
            String lastname,String email,String company,
            String password,String confirmpassword, String gender, String newsletters,
            String dayBirth,String monthBirth,String yearBirth) {

        nombre.sendKeys(firstname);
        apellido.sendKeys(lastname);
        correo.sendKeys(email);
        pass.sendKeys(password);
        confirmPass.sendKeys(confirmpassword);
        compania.sendKeys(company);

        if (newsletters.equals("y"))
        {
            noticias.click();
        }
        
        if (gender.equals("m"))
        {
            generoMasculino.click();
         //   Assert.assertTrue(maleRadioBtn.isSelected());
        }
        else if (gender == "f")
        {
            generoFemenino.click();
           // Assert.assertTrue(femaleRadioBtn.isSelected());
        }



    }
}
