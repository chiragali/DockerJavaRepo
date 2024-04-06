package page_tests;

import com.aventstack.extentreports.Status;
import org.json.JSONException;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;
import utils.FileUtils;

import java.io.IOException;
import java.util.Map;

public class ProductsPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    FileUtils fileUtils = new FileUtils();
    @Test
    public void addToCart() throws InterruptedException, JSONException, IOException {

        loginPageObject = new LoginPageObject(driver);

        testLogger.get().log(Status.INFO, "Step "+1+"- "+"Enter Login Credentials");
        productsPageObject = loginPageObject.userLogin();

        testLogger.get().log(Status.INFO, "Step "+2+"- "+"click Add To CartBtn");
        productsPageObject.clickAddToCartBtn();

        testLogger.get().log(Status.INFO, "Step "+3+"- "+"click Cart Icon");
        productsPageObject.clickCartIcon();

        testLogger.get().log(Status.INFO, "Step "+4+"- "+"verify Item Added To Cart");
        productsPageObject.verifyItemAddedToCart();

    }

    @Test
    public void checkOut() throws InterruptedException, JSONException, IOException {

        loginPageObject = new LoginPageObject(driver);

        //FileUtils.testStep("Enter Login Credentials", 1);

        testLogger.get().log(Status.INFO, "Step "+1+"- "+"Enter Login Credentials");
        productsPageObject = loginPageObject.userLogin();

        FileUtils.testStep("Get Product Price", 2, testLogger);
        String prodPrice = productsPageObject.getProductPrice();

        FileUtils.testStep("click Add To Cart Btn", 3, testLogger);
        productsPageObject.clickAddToCartBtn();

        FileUtils.testStep("click Cart Icon", 4, testLogger);
        productsPageObject.clickCartIcon();

        FileUtils.testStep("verify Ite mAdded To Cart", 5, testLogger);
        productsPageObject.verifyItemAddedToCart();

        FileUtils.testStep("verify Product in Cart", 6, testLogger);
        productsPageObject.verifyProductinCart(prodPrice);

        FileUtils.testStep("click on Checkout", 7, testLogger);
        productsPageObject.clickToCheckout();

        FileUtils.testStep("Enter Check Out User Info", 8, testLogger);
        productsPageObject.checkOutUserInfo();

        FileUtils.testStep("click To Continue", 9, testLogger);
        productsPageObject.clickToContinue();

        FileUtils.testStep("verify Item Total Price", 10, testLogger);
        productsPageObject.verifyItemTotalPrice(prodPrice);

        FileUtils.testStep("click Finish Btn", 11, testLogger);
        productsPageObject.clickFinishBtn();

        FileUtils.testStep("verify Thank You Msg", 12, testLogger);
        productsPageObject.verifyThankYouMsg();

    }

    @Test
    public void checkOutMultipleProduct() throws InterruptedException, JSONException, IOException {

        loginPageObject = new LoginPageObject(driver);

        int step=0;

        FileUtils.testStep("Enter Login Credentials", step++, testLogger);
        productsPageObject = loginPageObject.userLogin();

        FileUtils.testStep("Get Product Details", step++, testLogger);
        Map<String, String> productDetails_1  = productsPageObject.getProductDetails(1);
        Map<String, String> productDetails_2  = productsPageObject.getProductDetails(2);

        System.out.println(" Product 1 is --"+productDetails_1.get("Name"));
        System.out.println(" Product 2 is --"+productDetails_2.get("Name"));

        FileUtils.testStep("Click Add To Cart Btn Using Product Name", step++, testLogger);
        productsPageObject.clickAddToCartBtnUsingProductName(productDetails_1.get("Name"));

        FileUtils.testStep("Click Add To Cart Btn Using Product Name", step++, testLogger);
        productsPageObject.clickAddToCartBtnUsingProductName(productDetails_2.get("Name"));

        FileUtils.testStep("Click Cart Icon", step++, testLogger);
        productsPageObject.clickCartIcon();

        FileUtils.testStep("Verify Item Added To Cart", step++, testLogger);
        productsPageObject.verifyItemAddedToCart();

        System.out.println(" Price 1 is --"+productDetails_1.get("Price"));
        System.out.println(" Price 2 is --"+productDetails_2.get("Price"));

        FileUtils.testStep("verify Product "+productDetails_1.get("Name")+ "in Cart", step++, testLogger);
        productsPageObject.verifyProductinMultipleCart(productDetails_1.get("Price"), productDetails_1.get("Name"));

        FileUtils.testStep("verify Product "+productDetails_2.get("Name")+ "in Cart", step++, testLogger);
        productsPageObject.verifyProductinMultipleCart(productDetails_2.get("Price"), productDetails_2.get("Name"));

        FileUtils.testStep("check Out User Info", step++, testLogger);
        productsPageObject.clickToCheckout();

        FileUtils.testStep("clickAddToCartBtnUsingProductName", step++, testLogger);
        productsPageObject.checkOutUserInfo();

        FileUtils.testStep("click To Continue", step++, testLogger);
        productsPageObject.clickToContinue();

        FileUtils.testStep("verify Product "+productDetails_1.get("Name")+ "in Checkout", step++, testLogger);
        productsPageObject.verifyProductinMultipleCart(productDetails_1.get("Price"), productDetails_1.get("Name"));

        FileUtils.testStep("verify Product "+productDetails_2.get("Name")+ "in Checkout", step++, testLogger);
        productsPageObject.verifyProductinMultipleCart(productDetails_2.get("Price"), productDetails_2.get("Name"));

        FileUtils.testStep("Get Total Price", step++, testLogger);
        String totalPrice = productsPageObject.getTotalPrice(productDetails_1.get("Price"), productDetails_2.get("Price"));

        FileUtils.testStep("verify Item Total Price", step++, testLogger);
        productsPageObject.verifyItemTotalPrice(totalPrice);

        FileUtils.testStep("Click Finish Btn", step++, testLogger);
        productsPageObject.clickFinishBtn();

        FileUtils.testStep("Verify Thank You Msg", step++, testLogger);
        productsPageObject.verifyThankYouMsg();

    }

    @Test
    public void FailedAddToCart() throws InterruptedException, JSONException, IOException {

        loginPageObject = new LoginPageObject(driver);

        //FileUtils.testStep("Enter Login Credentials", 1);

        testLogger.get().log(Status.INFO, "Step "+1+"- "+"Enter Login Credentials");
        productsPageObject = loginPageObject.userLogin();

        FileUtils.testStep("Get Product Price", 2, testLogger);
        String prodPrice = "$121";//productsPageObject.getProductPrice();

        FileUtils.testStep("click Add To Cart Btn", 3, testLogger);
        productsPageObject.clickAddToCartBtn();

        FileUtils.testStep("click Cart Icon", 4, testLogger);
        productsPageObject.clickCartIcon();

        FileUtils.testStep("verify Ite mAdded To Cart", 5, testLogger);
        productsPageObject.verifyItemAddedToCart();

        FileUtils.testStep("verify Product in Cart", 6, testLogger);
        productsPageObject.verifyProductinCart(prodPrice);

    }

}
