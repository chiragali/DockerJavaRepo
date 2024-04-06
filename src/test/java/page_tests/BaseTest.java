package page_tests;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import base.AppConstants;
//import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

import static utils.ExtentReportHelper.getReportObject;

//import static utils.ExtentReportHelper.getReportObject;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();

    //private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(@Optional String browserName, ITestResult iTestResult){

        ChromeOptions co = new ChromeOptions();
        FirefoxOptions fo = new FirefoxOptions();


        if(browserName!=null){
            browser = browserName;
        }
        else {
            browser = AppConstants.browserName;
        }

        if(browser.equalsIgnoreCase(("chrome"))){
            if(AppConstants.platform.equalsIgnoreCase("local")){
                System.out.println(" Local Browser is -- "+browserName);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }

            else if (AppConstants.platform.equalsIgnoreCase("remote"))
            {
                System.out.println(" Remote Browser is -- "+browserName);

                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for Selenium Grid
                  // driver = new RemoteWebDriver(new URL("http://localhost:4444"), co);

                    // grid firefox & mention your system IP address instead of localhost
                    driver = new RemoteWebDriver(new URL(AppConstants.ipAddressUrl), co);
//                    String url = fileUtils.getApplicationProperty("demoUrl");
//                    goToApplication(url);

                    //remote webdriver url for Selenium standalone browser
                  // driver = new RemoteWebDriver(new URL("http://localhost:4441"), co);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }

            else if(AppConstants.platform.equalsIgnoreCase("remote_git"))
            {
                System.out.println(" Remote Git Browser is -- "+browserName);

                co.addArguments("--headless"); //for GitHub actions
                co.addArguments("--disable-gpu");
                co.addArguments("--no-sandbox");
                co.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(co);
            }

        }

        if(browser.equalsIgnoreCase(("firefox"))){
            if(AppConstants.platform.equalsIgnoreCase("local")){
                System.out.println(" Local Browser is -- "+browserName);

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            else if (AppConstants.platform.equalsIgnoreCase("remote"))
            {
                System.out.println(" Remote Browser is -- "+browserName);

                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for Selenium Grid
                    //driver = new RemoteWebDriver(new URL("http://localhost:4444"), fo);



                    //remote webdriver url for Selenium standalone browser
                   // driver = new RemoteWebDriver(new URL("http://localhost:4442"), fo);

                 // grid firefox & mention your system IP address instead of loca
                    driver = new RemoteWebDriver(new URL(AppConstants.ipAddressUrl), fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
//
            else if(AppConstants.platform.equalsIgnoreCase("remote_git"))
            {
                System.out.println(" Remote Git Browser is -- "+browserName);
                //testLogger.get().log(Status.INFO, "Docker Driver Started Time: "+ LocalDateTime.now());
                System.out.println("Docker Driver Started");
                fo.addArguments("--headless"); //for GitHub actions
                fo.addArguments("--disable-gpu");
                fo.addArguments("--no-sandbox");
                //  fo.addArguments("--remote-allow-origins=*"); not required for GitHub actions execution flow
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(fo);

            }

        }

        ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Driver Start Time: "+ LocalDateTime.now());

    }
    //driver = new RemoteWebDriver(new URL("http://192.168.29.170:4444/wd/hub"), co);

    //remote webdriver url for Selenium standalone browser

    @AfterMethod
    public void tearDown(ITestResult iTestResult){
        if(iTestResult.isSuccess())
        {
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName()+" is successful!!", ExtentColor.GREEN));
        }

        else {
            testLogger.get().log(Status.FAIL, "Test failed due to: "+ iTestResult.getThrowable());
            String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName()+".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertImg_Base64(screenshot)).build());
        }

        testLogger.get().log(Status.INFO, "Driver Docker End Time: "+ LocalDateTime.now());

        driver.quit();
    }

    @AfterClass
    public void flushTestReport()
    {
        reports.flush();
    }

    @BeforeSuite
    public void deletePreviousReports() {
        // Specify the path to the reports folder
        String reportsFolderPath = "reports";

        // Create a File object for the reports folder
        File reportsFolder = new File(reportsFolderPath);

        // Check if the reports folder exists
        if (reportsFolder.exists() && reportsFolder.isDirectory()) {
            // Get a list of all files in the reports folder
            File[] files = reportsFolder.listFiles();

            // If there are files, delete them
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
                System.out.println("Previous reports deleted successfully.");
            } else {
                System.out.println("No files found in the reports folder.");
            }
        } else {
            System.out.println("Reports folder does not exist.");
        }
    }

}
