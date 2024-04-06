package page_objects;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import utils.FileUtils;
import utils.WebElementsInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPageObject extends WebElementsInteractions {

    private final By userNameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");

    private final By loginBtn = By.id("login-button");
    FileUtils fileUtils = new FileUtils();

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }


    public ProductsPageObject userLogin() throws JSONException, IOException {

        String url = FileUtils.getenv();
        goToApplication(url);
        String username = FileUtils.getUserName();//fileUtils.getApplicationProperty("userName");
        String password = FileUtils.getPassword(); //fileUtils.getApplicationProperty("passWord");
        sendText(userNameTextField, username);
        sendText(passwordTextField, password);
        clickElement(loginBtn);

        return new ProductsPageObject(driver);

    }
}
