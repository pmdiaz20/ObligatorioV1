import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DataProviderClass;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class TestNopcommerce extends BaseTest{

    @Test(dataProvider = "SearchProviderToRegister",dataProviderClass = DataProviderClass.class)
    public void testCP1RegistrarUsuario(String gender,String firstname,String lastname,
                             String dayBirth,String monthBirth,String yearBirth,
                             String email,String company,String newsletter,
                             String password,String confirmpassword,Method method) throws InterruptedException{

        extentTest = extentReports.createTest(method.getName());

        SA.assertTrue(homePage.btnRegisterIsDisplayed(),"Falla porque no muestra btnRegister");
        extentTest.log(Status.INFO, "Valido que se muestra boton Register");
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
        extentTest.log(Status.INFO, "Chequeo que esten todos los campos displayed");
        extentTest.log(Status.INFO, "Procedo a completar los datos de registro");
        registerResultPage = registerPage.registerOK(gender,firstname,lastname,dayBirth,monthBirth,yearBirth,
                 email,company,newsletter,password,confirmpassword);
        extentTest.log(Status.INFO, "complete todos los datos de registro");

        SA.assertTrue((registerResultPage.txtOKRegisterIsDisplayed()));
        extentTest.log(Status.INFO, "Se muestra texto: Your registration completed");

        registerResultPage.clickContinuarInRegisterPageOK();
        extentTest.log(Status.INFO, "Click en continuar luego de registro OK");

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
        SA.assertTrue(homePage.btnMyAccountIsDisplayed(),"Falla en mostrar btnMyAccount");
        extentTest.log(Status.INFO, "Valido que estoy logueado");

        resultsPage= homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);
        SA.assertTrue(resultsPage.productoLocalizado(productToSearch),"Falla en validacion de encontrar producto");
        extentTest.log(Status.INFO, "Valido que encuentra producto");

   //     System.out.println("resultsPage.productoLocalizado(productToSearch): " + resultsPage.productoLocalizado(productToSearch));

        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed(),"Falla en validacion de AddToCart Displayed");
        extentTest.log(Status.INFO, "Valido que aparece boton AddToCart");

     //   System.out.println("resultsPage.btnAddToCartIsDisplayed(): "+ resultsPage.btnAddToCartIsDisplayed());

        resultsPage.addProductToShoppingCart();
        extentTest.log(Status.INFO, "Agrego articulo " + productToSearch + " al carrito");
        driver.navigate().refresh();

        shoppingCartPage = homePage.clickInShoppingCart();
        extentTest.log(Status.INFO, "Voy al carrito");
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart(productToSearch), "Falla en encontrar producto en carrito");

     //   System.out.println("shoppingCartPage.productIsInShoppingCart(productToSearch): "+shoppingCartPage.productIsInShoppingCart(productToSearch));

        extentTest.log(Status.INFO, "Valido que el producto "+ productToSearch + " esté en el carrito");

        SA.assertTrue(shoppingCartPage.btnCheckoutIsDisplayed(),"No se encuentra boton checkout");
        extentTest.log(Status.INFO, "Valido que aparezca el boton checkout");
        checkoutPage= shoppingCartPage.clickInCheckout();
        extentTest.log(Status.INFO, "Clickeo en Checkout");

        checkoutCompletePage= checkoutPage.realizarCheckoutConEfectivo(pais,ciudad,direccion1,direccion2,codigoPostal,telefono,fax);
        extentTest.log(Status.INFO, "Completé los datos para el checkout");
        SA.assertTrue(checkoutCompletePage.txtOrderOkIsDisplayed(),"No se mostro texto de orden procesada ok");
      //  System.out.println("checkoutCompletePage.txtOrderOkIsDisplayed(): "+ checkoutCompletePage.txtOrderOkIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestra texto: Your order has been successfully processed!'");

        extentTest.log(Status.INFO, checkoutCompletePage.txtOrderNumber());
        checkoutCompletePage.txtOrderNumber();

        SA.assertTrue(checkoutCompletePage.btnFinalizerOrderIsDisplayed(),"Falla en encontrar boton finalizar orden");
        extentTest.log(Status.INFO, "Valido que se muestra boton de finalizar orden");
       // System.out.println("checkoutCompletePage.btnFinalizerOrderIsDisplayed(): "+ checkoutCompletePage.btnFinalizerOrderIsDisplayed());
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
        SA.assertTrue(homePage.btnMyAccountIsDisplayed(),"Falla en mostrar btnMyAccount");
        extentTest.log(Status.INFO, "Valido que estoy logueado");

        resultsPage= homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);
        SA.assertTrue(resultsPage.productoLocalizado(productToSearch));
        extentTest.log(Status.INFO,"Valido que encuentra el producto "+ productToSearch);
        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestra boton AddToCart");
        resultsPage.addProductToShoppingCart();
        extentTest.log(Status.INFO, "Agrego articulo " + productToSearch + "al carrito");
        driver.navigate().refresh();
        shoppingCartPage = homePage.clickInShoppingCart();
        extentTest.log(Status.INFO, "Voy al carrito" );
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart(productToSearch));
        extentTest.log(Status.INFO, "Valido que el producto "+ productToSearch + " esté en el carrito");
        SA.assertTrue(shoppingCartPage.btnCheckoutIsDisplayed());
        extentTest.log(Status.INFO, "Valido que aparezca el boton checkout");
        checkoutPage= shoppingCartPage.clickInCheckout();
        extentTest.log(Status.INFO, "Clickeo en checkout");
        checkoutCompletePage = checkoutPage.realizarCheckoutConTarjeta(pais,ciudad,direccion1,direccion2,codigoPostal,
                telefono,fax,marcaTarjeta,titularTarjeta, numeroTarjeta, expiracionMes,expriacionAnio, codigoSeguridad);
        extentTest.log(Status.INFO, "Completo ok los datos");
        SA.assertTrue(checkoutCompletePage.txtOrderOkIsDisplayed());
        extentTest.log(Status.INFO, "Valido que texto 'Your order has been successfully processed!' sea visible");
        extentTest.log(Status.INFO, checkoutCompletePage.txtOrderNumber());
        SA.assertTrue(checkoutCompletePage.btnFinalizerOrderIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestra boton de finalizar orden");

        checkoutCompletePage.finalizeOrder();extentTest.log(Status.INFO, "Finalizo orden");
        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP3CheckoutConTarjeta");

    }


    @Test(dataProvider = "UserOkWithProductToWishList", dataProviderClass = DataProviderClass.class)
    public void testCP4agregarAWishList(String valid_user, String valid_password, String productToSearch, Method method) {
        extentTest = extentReports.createTest(method.getName());
        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);

        SA.assertTrue(homePage.btnMyAccountIsDisplayed(),"Falla en mostrar btnMyAccount");

        extentTest.log(Status.INFO, "Valido que estoy logueado");
        extentTest.log(Status.INFO, "Login Ok para correr testCP4agregarAWishList");

        resultsPage = homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);

        SA.assertTrue(resultsPage.productoLocalizado(productToSearch));
        extentTest.log(Status.INFO,"Valido que encuentra el producto "+ productToSearch);

        SA.assertTrue(resultsPage.btnAddToWishListIsDisplayed());
        extentTest.log(Status.INFO,"Valido que esta btn de agregar a Wishlist");


        resultsPage.addToWishList(productToSearch);
        extentTest.log(Status.INFO, "Agrego " + productToSearch + " a la Wishlist");
        SA.assertTrue(resultsPage.productAddedToWishList(productToSearch));
        extentTest.log(Status.INFO, "Valido que aparezca texto de que se agrega ok " + productToSearch + " a la Wishlist");driver.navigate().refresh();

        wishListPage = homePage.clickInWishList();extentTest.log(Status.INFO, "Voy a Wishlist");
        SA.assertTrue(wishListPage.chequearSiEstoyEnWishListPage());
        extentTest.log(Status.INFO, "Valido que llegue a WishlistPage");
        SA.assertTrue(wishListPage.chequearSiEstaProductoEnWishlist(productToSearch));
        extentTest.log(Status.INFO, "Valido que "+productToSearch+" se encuentre en wishlist");

        homePage.clickInlogout();
        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP4agregarAWishList");

    }


    @Test(dataProvider = "UserOkWithProductsToCompare", dataProviderClass = DataProviderClass.class)
    public void testCP5CompararProductos (String valid_user, String valid_password, String productToSearch, Method method)
    {
        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(valid_user,valid_password);
        SA.assertTrue(homePage.btnMyAccountIsDisplayed(),"Falla en mostrar btnMyAccount");
        extentTest.log(Status.INFO, "Valido que estoy logueado");

        extentTest.log(Status.INFO, "Logueado Ok para correr testCP5CompararProductos");
        resultsPage = homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Realizo una búsqueda que devuelva más de un resultado");
        extentTest.log(Status.INFO, "Palabra que devuelve mas de un resultado: "+productToSearch);
        SA.assertTrue(resultsPage.busquedaDevuelveMasdeUnProducto());
        extentTest.log(Status.INFO,"Valido que busqueda retorna mas de un producto");
        extentTest.log(Status.INFO, "Cantidad de productos encontrados: "+resultsPage.cantidadDeProductos());
        extentTest.log(Status.INFO, "Productos encontrados: "+resultsPage.nombreDeProductosEncontrados());
        SA.assertTrue(resultsPage.btnAddToCompareIsDisplayed());
        ArrayList<String> productos = new ArrayList<>();
        productos= resultsPage.nombreDeProductosEncontrados();
        extentTest.log(Status.INFO,"Valido que boton Add to compare se encuentre");

        resultsPage.addToCompareList(productToSearch);
        extentTest.log(Status.INFO, "Agrego los productos a compareList");

        SA.assertTrue(resultsPage.compareListLinkExist());
        extentTest.log(Status.INFO,"Valido que el link a CompareList este");
        compareProductsPage=resultsPage.clickInCompareList();
        SA.assertTrue(compareProductsPage.estoyEnComparePage());
        extentTest.log(Status.INFO,"Voy a ComparePage y valido que estoy en esa pagina");
        SA.assertFalse(compareProductsPage.compareListIsEmpty());//assertfalse
        extentTest.log(Status.INFO,"Valido que NO aparece texto 'You have no items to compare.'");            driver.navigate().refresh();

        SA.assertTrue(compareProductsPage.VerificarSiEstanProductosEnCompareList(productos));
        extentTest.log(Status.INFO,"Valido que esten los productos en compareList");

        homePage.clickInlogout();
        extentTest.log(Status.INFO, "Me deslogueo y finalizo testCP5CompararProductos");


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


    @Test(dataProvider = "UserOkWithProductToSendFriend", dataProviderClass = DataProviderClass.class)
    public void testCP7ExtraEmailAFriend(String mail,String password,String productToSearchAndSend,String emailFriend, Method method) {
        extentTest = extentReports.createTest(method.getName());
        loginPage = homePage.clickInLogIn();
        homePage= loginPage.login(mail,password);
        SA.assertTrue(homePage.btnMyAccountIsDisplayed());
        extentTest.log(Status.INFO, "Valido que estoy logueado porque aparece btnMyAccount");
        extentTest.log(Status.INFO, "Logueado Ok para correr testCP7ExtraEmailAFriend");

        resultsPage = homePage.searchProduct(productToSearchAndSend);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearchAndSend);

        infoProductPage = resultsPage.verDetalle(productToSearchAndSend);

        extentTest.log(Status.INFO, "Llego a infoProductPage para ver detalle de " + productToSearchAndSend);

        productEmailAFriendPage = infoProductPage.sendProduct();

        SA.assertTrue(productEmailAFriendPage.estoyEnEmailAFriend());
        extentTest.log(Status.INFO, "Valido que llego a pagina de envio de mail");

        SA.assertTrue(productEmailAFriendPage.inputFriendEmailIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre campo 'Friend's email'");

        SA.assertTrue(productEmailAFriendPage.inputMyEmailIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre campo 'Your email address'");

        SA.assertTrue(productEmailAFriendPage.boxMensajePersonalIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre cuadro de text 'Personal message'");

        SA.assertTrue(productEmailAFriendPage.btnSendEmailIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre boton sendEmail");


        productEmailAFriendPage.sendEmail(productToSearchAndSend,emailFriend);
        extentTest.log(Status.INFO, "Completo mail de amigo y le envio el producto");
        SA.assertTrue(productEmailAFriendPage.msgSendOK());
        extentTest.log(Status.INFO, "Valido que aparezca mensaje 'Your message has been sent'");
        homePage.clickInlogout();

        extentTest.log(Status.INFO, "Me deslogueo y finalizo correctamente testCP8ExtraEmailAFriend");


    }


    @Test(dataProvider = "UserOkT0ChangePassword",dataProviderClass = DataProviderClass.class)
    public void testCP8ExtraCambioDePassowrd(String mail,String password,String passwordNuevo,
                                                     String confirmarPasswordNuevo,Method method) {

        extentTest = extentReports.createTest(method.getName());

        loginPage = homePage.clickInLogIn();
        loginPage.login(mail,password);
        extentTest.log(Status.INFO, "Logueado ok");
        SA.assertTrue(homePage.btnMyAccountIsDisplayed());extentTest.log(Status.INFO, "Valido login ok");

        customerPage= homePage.clickInMyAccount();
        extentTest.log(Status.INFO, "Clickeo en MyAccount");
        SA.assertTrue(customerPage.changePasswordIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre el link para cambio de password");
        changePasswordPage= customerPage.goToChangePassword();
        SA.assertTrue(changePasswordPage.estoyEnChangePasswordPage());
        extentTest.log(Status.INFO, "Valido que llego a sección My account - Change password");
        extentTest.log(Status.INFO, "Clickeo en ChangePassword y paso a completar datos");

        SA.assertTrue(changePasswordPage.inputOldPasswordIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre campo 'OldPassword'");
        SA.assertTrue(changePasswordPage.inputNewPasswordIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre campo 'NewPassword'");
        SA.assertTrue(changePasswordPage.inputConfirmNewPasswordIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre campo 'ConfirmNewPassword'");
        SA.assertTrue(changePasswordPage.btnChangePasswordIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre boton 'ChangePassword'");

        extentTest.log(Status.INFO, "OldPassWord: " + password +"  newPassWord: "+passwordNuevo+"  confirmNewPassWord: " + confirmarPasswordNuevo);
        changePasswordPage.changePasswordOk(password,passwordNuevo,confirmarPasswordNuevo);


        SA.assertTrue(changePasswordPage.txtPasswordChangedOkIsDisplayed());
        extentTest.log(Status.INFO, "Se cambio la contraseña correctamente");
        extentTest.log(Status.INFO, "La nueva password del mail "+mail+" es "+passwordNuevo);
        homePage.clickInlogout();
        extentTest.log(Status.INFO, "Logout");
        extentTest.log(Status.INFO, "Intento loguearme con mail '"+mail+"' y nueva password '"+passwordNuevo+"'");

        loginPage = homePage.clickInLogIn();
        loginPage.login(mail,passwordNuevo);
        extentTest.log(Status.INFO, "Valido Login ok con nueva password: "+passwordNuevo);

        SA.assertTrue(homePage.btnMyAccountIsDisplayed());
        extentTest.log(Status.INFO, "Logueado ok");


        extentTest.log(Status.INFO, "Me deslogueo y finalizo correctamente testCP7ExtraCambioDeMail");

    }


    @Parameters("currency")
    @Test
    public void testCP9ExtracambioMonedaParameters(String moneda, Method method){
        extentTest = extentReports.createTest(method.getName());
        extentTest.log(Status.INFO, "Busco producto y luego cambio a tipo de moneda "+moneda);

        resultsPage = homePage.searchProduct("Nokia Lumia 1020");
        SA.assertTrue(homePage.currencyListIsDisplayed(),"Falla en mostrar lista de monedas");
        extentTest.log(Status.INFO, "Valido que se muestre la lista de monedas");
        homePage.seleccionarMoneda(moneda);
        Assert.assertTrue(homePage.checkChangeCurrency(moneda));
    }


    @Test
    public void testCP10ExtraAgregarLibroMasEconomicoACarrito(Method method){
        extentTest = extentReports.createTest(method.getName());
        extentTest.log(Status.INFO, "Voy a la seccion libros");

        resultsPage = homePage.gotoBooks();

        resultsPage.sortByPriceLowToHigh();
        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());

        resultsPage.agregarAlCarritoMenorPrecio();
        //resultsPage.addProductToShoppingCart();
    }

    @Test void testCP10ExtraAgregarACarritoyLuegoEliminarlo(Method method){
        extentTest = extentReports.createTest(method.getName());

        String productToSearch = "Asus N551JK-XO076H Laptop";
       // loginPage = homePage.clickInLogIn();
        //loginPage.login(valid_user,valid_password);
        resultsPage= homePage.searchProduct(productToSearch);
        extentTest.log(Status.INFO, "Buscando articulo " + productToSearch);

        SA.assertTrue(resultsPage.productoLocalizado(productToSearch));
        extentTest.log(Status.INFO,"Valido que encuentra el producto "+ productToSearch);

        SA.assertTrue(resultsPage.btnAddToCartIsDisplayed());
        extentTest.log(Status.INFO, "Valido que se muestre boton AddToCart");

        resultsPage.addProductToShoppingCart();
        driver.navigate().refresh();
        extentTest.log(Status.INFO, "Agregando articulo " + productToSearch + "al carrito");
        shoppingCartPage = homePage.clickInShoppingCart();
        extentTest.log(Status.INFO, "Voy al carrito" );
        SA.assertTrue(shoppingCartPage.productIsInShoppingCart(productToSearch));
        extentTest.log(Status.INFO, "Valido que el producto este en el carrito");

        SA.assertTrue(shoppingCartPage.checkBoxDeleteIsDisplayed());
        extentTest.log(Status.INFO, "Valido que el checkBox para remover se muestre");

        SA.assertTrue(shoppingCartPage.btnUpdateCartIsDisplayed());
        extentTest.log(Status.INFO, "Valido que boton UpdateCart se muestre");
        shoppingCartPage.delete();
        extentTest.log(Status.INFO, "Elimino producto");
        SA.assertTrue(shoppingCartPage.emptyCart());
        extentTest.log(Status.INFO, "Valido que carrito no tenga productos");


    }


}// Fin clase