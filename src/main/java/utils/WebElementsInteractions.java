package utils;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class WebElementsInteractions {

    protected WebDriver driver;
    WebDriverWait wait;

//    BaseTest baseTest = new BaseTest();


    //Custom Keywords that helps to implement KeyWord Driven Framework Strategy
    protected WebElementsInteractions(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected void clickElement(By locator)
    {
        driver.findElement(locator).click();
    }

    protected void sendText(By locator, String text)
    {
        driver.findElement(locator).sendKeys(text);
    }

    protected void goToApplication(String url) throws JSONException, IOException {

        driver.get(FileUtils.geturl());
        driver.manage().window().maximize();
    }

    protected String retrieveTextData(By locator)
    {
        return driver.findElement(locator).getText();
    }

    public void waitForvisibilityOfElement(By locator){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    public void waitForpresenceOfElement(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }



}
