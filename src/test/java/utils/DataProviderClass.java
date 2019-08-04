package utils;

import org.testng.annotations.DataProvider;


public class DataProviderClass {

    @DataProvider(name = "SearchProviderToRegister")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        {"M","Juan","Perez","11","September","1980","mailtest0002@mail.com","Empresa SA","YES","pass00","pass00"}
                };
    }


    @DataProvider(name = "UserOkWithProduct")
    public static Object[][] getDataFromDataProviderUserOkWithProduct(){

        return new Object[][]
                {
                        {"mail_00@mail.com", "pass00","Asus N551JK-XO076H Laptop","Uruguay","Montevideo",
                                "Avenida Italia 1221","Avenida Italia 1222 Bis","12400","26095815","26095817"}
                };

    }

    @DataProvider(name = "UserOkWithProductWithCreditCard")
    public static Object[][] UserOkWithProductWithCreditCard(){

        return new Object[][]
                {
                        {"mail_00@mail.com", "pass00","Asus N551JK-XO076H Laptop","Uruguay","Montevideo",
                                "Avenida Italia 1221","Avenida Italia 1222 Bis","12400","26095815","26095817",
                        "MasterCard","Juan Arturo Sosa","5197136294124627","11","2020","336"}
                };

    }


    @DataProvider(name = "UserOkWithProductToWishList")
    public static Object[][] getDataFromDataProviderUserOkWithProductToWishList(){
        return new Object[][]
                {
                        {"mail_00@mail.com", "pass00","HTC One M8 Android L 5.0 Lollipop"},
                        {"mail_00@mail.com", "pass00","Asus N551JK-XO076H Laptop"},
                        {"mail_00@mail.com", "pass00","Fahrenheit 451 by Ray Bradbury"},
                        {"mail_00@mail.com", "pass00","Nokia Lumia 1020"},
                        {"mail_00@mail.com", "pass00","Flower Girl Bracelet"}
                };

    }


    @DataProvider(name = "UserOkWithProductsToCompare")
    public static Object[][] getDataFromDataProviderUserOkWithProductsToCompare(){
        return new Object[][]
                {
                        {"mail_00@mail.com", "pass00","HTC"}
                };
    }

    @DataProvider(name = "UserOkT0ChangePassword")
    public static Object[][] getDataFromDataProviderUserOkT0ChangePassword(){
        return new Object[][]
                {        //mail                ,oldpasword, newpassword, confirmNewPassword

                        {"mailtest0002@mail.com", "pass00","pass01","pass01"}
                };
    }



    @DataProvider(name = "UserOkWithProductToSendFriend")
    public static Object[][] getDataFromDataProviderUserOkWithProductToSendFriend(){
        return new Object[][]
                {
                        {"mail_00@mail.com","pass00","Nokia Lumia 1020","mailamigo@mail.com"}
                };

    }


}
