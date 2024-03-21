package page_tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class ProductsPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;


//    public ProductsPageTests(WebDriver driver) {
//        super();
//    }

    @Test
    public void itemTest(){


        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = new ProductsPageObject(driver);
        loginPageObject.userLogin(username, password);
        System.out.println(productsPageObject.getTitleOfPage());
        System.out.println(productsPageObject.getItemName());

    }



}
