package utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider(name = "SearchProviderToRegister")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        {"M","Juan","Perez","11","September","1980","amaila112200@mail.com","Empresa SA","YES","pass00","pass00"}

                };
    }


    @DataProvider(name = "UserOkWithProduct")
    public static Object[][] getDataFromDataProviderUserOkWithProduct(){

        return new Object[][]
                {
                        {"amaila112200@mail.com", "pass00","Asus N551JK-XO076H Laptop","Uruguay","Montevideo",
                                "Avenida Italia 1221","Avenida Italia 1222 Bis","12400","26095815","26095817"}
                };

    }

    @DataProvider(name = "UserOkWithProductWithCreditCard")
    public static Object[][] UserOkWithProductWithCreditCard(){

        return new Object[][]
                {
                        {"amaila112200@mail.com", "pass00","Asus N551JK-XO076H Laptop","Uruguay","Montevideo",
                                "Avenida Italia 1221","Avenida Italia 1222 Bis","12400","26095815","26095817",
                        "MasterCard","Juan Arturo Sosa","5197136294124627","11","2020","336"}
                };

    }





}
