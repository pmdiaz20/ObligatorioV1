import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataProviderClass;

import java.lang.reflect.Method;


public class TestNopcommerce extends BaseTest{

    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testCP1RegistrarUsuario(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword,Method method) throws InterruptedException{

        extentTest = extentReports.createTest(method.getName());

        registerPage = homePage.clickInRegister();
        extentTest.log(Status.INFO, "Clickeo en Register");


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

        extentTest.log(Status.INFO, "Procedo a completar los datos de registro");

        registerResultPage = registerPage.registerOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                 email,company,newsletter,password,confirmpassword);
        extentTest.log(Status.INFO, "complete todos los datos de registro");

        SA.assertTrue((registerResultPage.txtOKRegisterIsDisplayed()));

        registerResultPage.clickContinuarInRegisterPageOK();
        extentTest.log(Status.INFO, "Clickeo en continuar luego de registro OK");

        homePage.clickInlogout();
        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP1RegistrarUsuario");

    }


    @Test(dataProvider = "UserOkWithProduct",dataProviderClass = DataProviderClass.class)
    public void testCP2CheckoutConEfectivo(String valid_user,String valid_password,String productToSearch,
                                           String pais,String ciudad,String direccion1,String direccion2,
                                           String codigoPostal,String telefono,String fax,Method method){

        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        extentTest.log(Status.INFO, "Logueado ok");

        resultsPage= homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);

        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());

        resultsPage.addProductToShoppingCart();
        extentTest.log(Status.INFO, "agrego articulo " + productToSearch + " al carrito");

        shoppingCartPage = homePage.clickInShoppingCart();
        extentTest.log(Status.INFO, "Voy al carrito");
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart(productToSearch));
        extentTest.log(Status.INFO, "Valido que el producto "+ productToSearch + " esté en el carrito");

        checkoutPage= shoppingCartPage.clickInCheckout();
        extentTest.log(Status.INFO, "Clickeo en Checkout");

        checkoutCompletePage= checkoutPage.realizarCheckoutConEfectivo(pais,ciudad,direccion1,direccion2,codigoPostal,telefono,fax);
        SA.assertTrue(checkoutCompletePage.txtOrderOkIsDisplayed());
        extentTest.log(Status.INFO, "Completé los datos para el checkout");
        checkoutCompletePage.finalizeOrder();
        extentTest.log(Status.INFO, "Finalizo orden");
        homePage.clickInlogout();
        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP2CheckoutConEfectivo");

    }



    @Test(dataProvider = "UserOkWithProductWithCreditCard",dataProviderClass = DataProviderClass.class)
    public void testCP3CheckoutConTarjeta(String valid_user,String valid_password,String productToSearch,String pais, String ciudad, String direccion1, String direccion2,
    String codigoPostal, String telefono, String fax, String marcaTarjeta,
    String titularTarjeta,String numeroTarjeta,String expiracionMes,String expriacionAnio,
    String codigoSeguridad,Method method){

        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        resultsPage= homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);
        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());

        resultsPage.addProductToShoppingCart();
        extentTest.log(Status.INFO, "Agregando articulo " + productToSearch + "al carrito");

        extentTest.log(Status.INFO, "intento ir hasta arriba" );

        extentTest.log(Status.INFO, "fue hasta arriba" );

        shoppingCartPage = homePage.clickInShoppingCart();
        extentTest.log(Status.INFO, "Voy al carrito" );
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart(productToSearch));

        checkoutPage= shoppingCartPage.clickInCheckout();
        extentTest.log(Status.INFO, "Clickeo en checkout");
        checkoutCompletePage = checkoutPage.realizarCheckoutConTarjeta(pais,ciudad,direccion1,direccion2,codigoPostal,
                telefono,fax,marcaTarjeta,titularTarjeta, numeroTarjeta, expiracionMes,expriacionAnio, codigoSeguridad);
        extentTest.log(Status.INFO, "Completo ok los datos");

        SA.assertTrue(checkoutCompletePage.txtOrderOkIsDisplayed());

        checkoutCompletePage.finalizeOrder();
        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP3CheckoutConTarjeta");

    }

//usar data provider
    @Test(dataProvider = "UserOkWithProductToWishList", dataProviderClass = DataProviderClass.class)
    public void testCP4agregarAWishList(String valid_user, String valid_password, String productToSearch, Method method) {
        extentTest = extentReports.createTest(method.getName());
        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        extentTest.log(Status.INFO, "Login Ok para correr testCP4agregarAWishList");
        resultsPage = homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);

        resultsPage.addToWishList(productToSearch);

        extentTest.log(Status.INFO, "Agrego " + productToSearch + " a la Wishlist");

        Assert.assertTrue(resultsPage.productAddedToWishList(productToSearch));

        extentTest.log(Status.INFO, "Se agrega ok " + productToSearch + " a la Wishlist");

        driver.navigate().refresh();

        extentTest.log(Status.INFO, "refresco pagina");
      //  wishListPage = searchPage.goToWishList();
      //  Assert.assertTrue(wishListPage.verifyIfAt(productToSearch));


        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Click en logout");


        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP4agregarAWishList");

    }


    @Test(dataProvider = "UserOkWithProductsToCompare", dataProviderClass = DataProviderClass.class)
    public void testCP5CompararProductos (String valid_user, String valid_password, String productToSearch, Method method)
    {
        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        extentTest.log(Status.INFO, "Logueado Ok para correr testCP5CompararProductos");
        resultsPage = homePage.searchProduct(productToSearch);

        extentTest.log(Status.INFO, "Agrego " + productToSearch + " a compareList");

        resultsPage.addToCompareList(productToSearch);

        resultsPage.clickInCompareList();

        driver.navigate().refresh();

        // FALTA TERMINAR
    }



    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testCP6ExtraRegistroConMailExistente(String gender,String firstname,String lastname,
                                        String dayBirth,String monthBirth,String yearBirth,
                                        String email,String company,String newsletter,
                                        String password,String confirmpassword,Method method) throws InterruptedException{

        extentTest = extentReports.createTest(method.getName());

        registerPage = homePage.clickInRegister();
        extentTest.log(Status.INFO, "Clickeo en Register");


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

        extentTest.log(Status.INFO, "Procedo a completar los datos de registro");

        registerPage.registerNotOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                email,company,newsletter,password,confirmpassword);
        extentTest.log(Status.INFO, "complete todos los datos de registro");

        SA.assertTrue((registerPage.msjErrorMailExistente()));

        extentTest.log(Status.INFO, "Me aparece error al intentar registrar usuario con mail ya registrado");


    }



    @Test(dataProvider = "UserOkT0ChangePassword",dataProviderClass = DataProviderClass.class)
    public void testCP7ExtraCambioDeMail(String mail,String password,String passwordNuevo,
                                                     String confirmarPasswordNuevo,Method method) {

        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(mail,password);
        extentTest.log(Status.INFO, "Logueado ok");
        customerPage= homePage.clickInMyAccount();
        extentTest.log(Status.INFO, "Clickeo en MyAccount");
        changePasswordPage= customerPage.goToChangePassword();
        extentTest.log(Status.INFO, "Llego a sección My account - Customer info");
        extentTest.log(Status.INFO, "Clickeo en ChangePassword y paso a completar datos");
        extentTest.log(Status.INFO, "OldPassWord: " + password +"  newPassWord: "+passwordNuevo+"  confirmNewPassWord: " + confirmarPasswordNuevo);
        changePasswordPage.changePasswordOk(password,passwordNuevo,confirmarPasswordNuevo);

        if(changePasswordPage.newPassDistinctConfirmPass()){

            extentTest.log(Status.INFO, "Falla:nueva pass y confirmar nueva password no son iguales");
        }

        if(changePasswordPage.newPassIgualOldPass())
        {
            extentTest.log(Status.INFO, "Falla: newPassWord ingresado distinta es igual a OldPassword");
        }

        SA.assertTrue(changePasswordPage.txtPasswordChangedOkIsDisplayed());
        extentTest.log(Status.INFO, "Se cambio la contraseña correctamente");

        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Me deslogueo y finalizo correctamente testCP7ExtraCambioDeMail");

    }





    @Test(dataProvider = "UserOkWithProductToSendFriend", dataProviderClass = DataProviderClass.class)
    public void testCP8ExtraEmailAFriend(String valid_user, String valid_password, String productToSearchAndSend,String emailFriend, Method method) {
        extentTest = extentReports.createTest(method.getName());
        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user, valid_password);
        extentTest.log(Status.INFO, "Login Ok para correr testCP8ExtraEmailAFriend");
        resultsPage = homePage.searchProduct(productToSearchAndSend);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearchAndSend);

        infoProductPage = resultsPage.verDetalle(productToSearchAndSend);

        extentTest.log(Status.INFO, "Llego a infoProductPage para ver detalle de " + productToSearchAndSend);

        productEmailAFriendPage = infoProductPage.sendProduct();
        extentTest.log(Status.INFO, "Llego a la pagina de envio de mail");


        productEmailAFriendPage.sendEmail(productToSearchAndSend,emailFriend);
        extentTest.log(Status.INFO, "Completo mail de amigo y le envio el producto");
        SA.assertTrue(productEmailAFriendPage.msgSendOK());

        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Me deslogueo y finalizo correctamente testCP8ExtraEmailAFriend");





    }



}// Fin clase