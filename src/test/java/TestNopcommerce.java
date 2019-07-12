import org.testng.annotations.Test;
import utils.DataProviderClass;


public class TestNopcommerce extends BaseTest{



    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testCP1RegistrarUsuario(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword) throws InterruptedException{

        registerPage = homePage.clickInRegister();

        SA.assertTrue(registerPage.firstNameIsDisplayed());
        SA.assertTrue(registerPage.lastNameIsDisplayed());
        SA.assertTrue(registerPage.genderMaleIsDisplayed());
        SA.assertTrue(registerPage.genderFemaleIsDisplayed());
        SA.assertTrue(registerPage.emailIsDisplayed());
        SA.assertTrue(registerPage.companyIsDisplayed());
        SA.assertTrue(registerPage.newsletterIsDisplayed());
        SA.assertTrue(registerPage.passwordIsDisplayed());
        SA.assertTrue(registerPage.confirmPasswordIsDisplayed());
        SA.assertTrue(registerPage.btnRegisterIsDisplayed());
        SA.assertTrue(registerPage.dropDayBirthIsDisplayed());
        SA.assertTrue(registerPage.dropMonthBirthIsDisplayed());
        SA.assertTrue(registerPage.dropYearBirthIsDisplayed());

        registerResultPage = registerPage.registerOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                 email,company,newsletter,password,confirmpassword);

        SA.assertTrue((registerResultPage.txtOKRegisterIsDisplayed()));

    }


    @Test(dataProvider = "UserOkWithProduct",dataProviderClass = DataProviderClass.class)
    public void testCP2CheckoutConEfectivo(String valid_user,String valid_password,String productToSearch,
                                           String pais,String ciudad,String direccion1,String direccion2,
                                           String codigoPostal,String telefono,String fax){

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        resultsPage= homePage.searchProduct(productToSearch);
        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());

        resultsPage.addProductToShoppingCart();
        shoppingCartPage = homePage.clickInShoppingCart();
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart());

        checkoutPage= shoppingCartPage.clickInCheckout();
        checkoutCompletePage= checkoutPage.realizarCheckoutConEfectivo(pais,ciudad,direccion1,direccion2,codigoPostal,telefono,fax);
        SA.assertTrue(checkoutCompletePage.txtOrderOkIsDisplayed());

        checkoutCompletePage.finalizeOrder();


    }






}// Fin clase
