package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebElementsInteractions;

public class ProductsPageObject extends WebElementsInteractions {

    WebDriver driver;
    private final By getTitleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");
    //private final By getTextOf1stItem = By.xpath("//a[@id='item_111_title_link']/div");
    private final By getTextOf1stItem = By.xpath("//a[@id='item_4_title_link']/div");

    public ProductsPageObject(WebDriver driver) {
        super(driver);
    }

//    public ProductsPageObject userLogin(String username, String password) {
//
//        goToApplication("https://www.saucedemo.com/");
//        sendText(userNameTextField, username);
//        sendText(passwordTextField, password);
//        clickElement(loginBtn);
//
//        return new ProductsPageObject(driver);
//
//    }

    public String getTitleOfPage()
    {
        return retrieveTextData(getTitleOfProductPage);
    }

    public String getItemName()
    {
        return retrieveTextData(getTextOf1stItem);
    }
}
