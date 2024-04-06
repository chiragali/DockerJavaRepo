package page_objects;

import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebElementsInteractions;
//import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.Map;


public class ProductsPageObject extends WebElementsInteractions {

    WebDriver driver;
    private final By getTitleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");
    //private final By getTextOf1stItem = By.xpath("//a[@id='item_111_title_link']/div");
    private final By getTextOf1stItem = By.xpath("//a[@id='item_4_title_link']/div");
    private final By addToCartBtn = By.xpath("//div[text()='Sauce Labs Backpack']/../../..//div[@class='pricebar']//button");
    private final By cartIcon = By.xpath("//div[@class='shopping_cart_container']//a");
    public final By itemName = By.xpath("//div[@class=\"inventory_item_name\"]");
    private final By checkOutBtn = By.xpath("//button[text()=\"Checkout\"]");
    public final By firstNameTxtFld = By.xpath("//input[@id='first-name']");

    public final By lastNameTxtFld = By.xpath("//input[@id='last-name']");

    public final By postalCodeTxtFld = By.xpath("//input[@id='postal-code']");
    public final By continueBtn = By.xpath("//input[@value='Continue']");
    public final By finishBtn = By.xpath("//button[@id='finish']");
    public final By thankYouMsg = By.xpath("//h2[@class='complete-header']");
    public final By prodPrice = By.xpath("//*[text()=\"Sauce Labs Backpack\"]/../../..//div[@class=\"inventory_item_price\"]");
    public final By itemTotal = By.xpath("//div[@class='summary_subtotal_label']");

    public ProductsPageObject(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage()
    {
        return retrieveTextData(getTitleOfProductPage);
    }

    public String getItemName()
    {
        return retrieveTextData(getTextOf1stItem);
    }

    public void clickCartIcon() {
        waitForpresenceOfElement(cartIcon);
        clickElement(cartIcon);
    }

    public void verifyItemAddedToCart() {

        waitForpresenceOfElement(itemName);
        String actualItemName = retrieveTextData(itemName);
        String expectedItemName = "Sauce Labs Backpack";
        AssertJUnit.assertEquals("Item added to cart does not match expected", expectedItemName, actualItemName);

    }

    public void clickAddToCartBtn() {
        //Thread.sleep(6000);
        clickElement(addToCartBtn);

    }

    public void clickToCheckout() {

        waitForpresenceOfElement(checkOutBtn);
        clickElement(checkOutBtn);
    }

    public void checkOutUserInfo() {

        sendText(firstNameTxtFld, "Chirag");
        sendText(lastNameTxtFld, "Test");
        sendText(postalCodeTxtFld, "400102");

    }

    public void clickToContinue() {

        waitForpresenceOfElement(continueBtn);
        clickElement(continueBtn);
    }

    public void clickFinishBtn() {

        waitForpresenceOfElement(finishBtn);
        clickElement(finishBtn);
    }

    public void verifyThankYouMsg() {

        waitForpresenceOfElement(thankYouMsg);
        String actualItemName = retrieveTextData(thankYouMsg);
        String expectedItemName = "Thank you for your order!";
        AssertJUnit.assertEquals("Item checked out Successfully", expectedItemName, actualItemName);

    }

    public String getProductPrice() {

        return retrieveTextData(prodPrice);

    }

    public void verifyProductinCart(String expectedProdPrice) {

        waitForpresenceOfElement(prodPrice);
        String actualItemName = retrieveTextData(prodPrice);
        AssertJUnit.assertEquals("Item added to cart does not match expected", expectedProdPrice, actualItemName);

    }

    public void verifyProductinMultipleCart(String expectedProdPrice, String ProdName) {

        By itemTotal = By.xpath("//*[text()='" +ProdName+ "']/../../..//div[@class='inventory_item_price']");
        waitForpresenceOfElement(itemTotal);
        String actualItemPrice = retrieveTextData(itemTotal);
        AssertJUnit.assertEquals("Item added to cart does not match expected", expectedProdPrice, actualItemPrice);

    }

    public void verifyItemTotalPrice(String expectedProdPrice) {

        waitForpresenceOfElement(itemTotal);
        String actualItemName = retrieveTextData(itemTotal).split(":")[1].trim();
        System.out.println(" Price Item in checkout page is -- "+actualItemName);
        AssertJUnit.assertEquals("Item added to cart does not match expected", expectedProdPrice, actualItemName);

    }

    public String getProductName(int i) {
        By itemTotal = By.xpath("(//div[@class='inventory_item_name '])[" + i + "]");
        String productName = retrieveTextData(itemTotal);
        return productName;
    }

    public String getProductPrice(String prodName) {
        // Constructing XPath with parameter prodName
        By itemTotal = By.xpath("//*[text()='" +prodName+ "']/../../..//div[@class='inventory_item_price']");
        String productPrice = retrieveTextData(itemTotal);
        return productPrice;
    }

    public Map<String, String> getProductDetails(int i) {
        Map<String, String> productDetails = new HashMap<>();

        // Get product name
        String productName = getProductName(i);
        productDetails.put("Name", productName);

        // Get product price
        String productPrice = getProductPrice(productName);
        productDetails.put("Price", productPrice);

        return productDetails;
    }


    public void clickAddToCartBtnUsingProductName(String name) {

        By addToCartBtn = By.xpath("//div[text()='" +name+ "']/../../..//div[@class='pricebar']//button");
        clickElement(addToCartBtn);

    }

    public String getTotalPrice(String price1, String price2) {

        double price_1 = Double.parseDouble(price1.replace("$", ""));
        double price_2 = Double.parseDouble(price2.replace("$", ""));
        double totalPrice = price_1 + price_2;

        return String.valueOf("$"+totalPrice);
    }
}
