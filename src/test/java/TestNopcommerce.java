import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class TestNopcommerce {

    //http://demo.nopcommerce.com/


    /*
    CP1: Registrar Usuario:
    1. Navegar a Regiter.
    2. Completar datos obligatorios
    3. Realizar el registro
    4. Verificar que el registro fue satisfactorio
     */


    @Test
    public void test_NavegarARegister()
    {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement register = driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.header > div.header-upper > div.header-links-wrapper > div.header-links > ul > li:nth-child(1) > a"));
        register.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div > div > div > div.page-body > form > div:nth-child(1) > div.form-fields")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div > div > div > div.page-body > form > div:nth-child(2) > div.form-fields")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div > div > div > div.page-body > form > div:nth-child(3) > div.form-fields")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div > div > div > div.page-body > form > div:nth-child(4) > div.form-fields")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("register-button")).isDisplayed());

        String firstname="Pedro";
        String lastname= "Fernandez";
        String email= "pedrofernandez2133@gmail.com";
        String company= "PedFe S.A";
        String password= "pedro1234";
        String gender ="f";
        String confirmpassword= password;
        String dayBirth ="20";
        String monthBirth ="October";
        String yearBirth ="1982";

        //Datos obligatorios
        WebElement inputFirstName = driver.findElement(By.id("FirstName"));
        Assert.assertTrue(inputFirstName.isDisplayed());
        Assert.assertTrue(inputFirstName.isEnabled());
        inputFirstName.sendKeys(firstname);
        WebElement inputLastName = driver.findElement((By.id("LastName")));
        Assert.assertTrue(inputLastName.isDisplayed());
        Assert.assertTrue(inputLastName.isEnabled());
        inputLastName.sendKeys(lastname);
        WebElement inputEmail = driver.findElement(By.id("Email"));
        Assert.assertTrue(inputEmail.isDisplayed());
        Assert.assertTrue(inputEmail.isEnabled());
        inputEmail.sendKeys(email);
        WebElement inputPassword = driver.findElement(By.id("Password"));
        Assert.assertTrue(inputPassword.isDisplayed());
        Assert.assertTrue(inputPassword.isEnabled());
        inputPassword.sendKeys(password);
        WebElement inputConfirmPassword = driver.findElement(By.id("ConfirmPassword"));
        Assert.assertTrue(inputConfirmPassword.isDisplayed());
        Assert.assertTrue(inputConfirmPassword.isEnabled());
        inputConfirmPassword.sendKeys(confirmpassword);

        //Datos opcionales //Gender ,Date of birth 3 selects,Company name,Newsletter
        WebElement inputCompany = driver.findElement(By.id("Company"));
        Assert.assertTrue(inputCompany.isDisplayed());
        Assert.assertTrue(inputCompany.isEnabled());
        inputCompany.sendKeys(company);

        WebElement maleRadioBtn = driver.findElement(By.id("gender-male"));
        Assert.assertTrue(maleRadioBtn.isDisplayed());
        Assert.assertTrue(maleRadioBtn.isEnabled());
        WebElement femaleRadioBtn = driver.findElement(By.id("gender-female"));
        Assert.assertTrue(femaleRadioBtn.isDisplayed());
        Assert.assertTrue(femaleRadioBtn.isEnabled());

        if (gender == "m")
        {
            maleRadioBtn.click();
            Assert.assertTrue(maleRadioBtn.isSelected());
        }
        else if (gender == "f")
        {
            femaleRadioBtn.click();
            Assert.assertTrue(femaleRadioBtn.isSelected());
        }

        Select day = new Select(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[1]")));
        day.selectByValue(dayBirth);
        Select month = new Select(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[2]")));
        month.selectByVisibleText(monthBirth);
        Select year = new Select(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[3]")));
        year.selectByVisibleText(yearBirth);

        WebElement newsletter = driver.findElement(By.id("Newsletter"));
        newsletter.click();

        WebElement btnRegister = driver.findElement(By.id("register-button"));
        btnRegister.click();

        Assert.assertEquals(driver.findElement(By.className("result")).getText(),"Your registration completed");

       // driver.quit(); //cierro todo

    }






}
