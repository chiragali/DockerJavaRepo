package page_tests;

import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class LoginPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;

    @Test
    public void userLoginTest(){


        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = new ProductsPageObject(driver);
        productsPageObject = loginPageObject.userLogin(username, password);
        System.out.println(productsPageObject.getTitleOfPage());

    }



}
