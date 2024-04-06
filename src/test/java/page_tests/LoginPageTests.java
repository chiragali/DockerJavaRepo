package page_tests;

import com.aventstack.extentreports.Status;
import org.json.JSONException;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;
import utils.FileUtils;
import utils.WebElementsInteractions;

import java.io.File;
import java.io.IOException;

public class LoginPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    FileUtils fileUtils = new FileUtils();
    @Test
    public void userLoginTest() throws JSONException, IOException {

        loginPageObject = new LoginPageObject(driver);
        productsPageObject = loginPageObject.userLogin();

        testLogger.get().log(Status.INFO, "Step "+2+"- "+"Verify Home Page is displayed");
        productsPageObject.clickAddToCartBtn();

    }



}
