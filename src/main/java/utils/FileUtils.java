package utils;

import base.AppConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static utils.ExtentReportHelper.getReportObject;

public class FileUtils {

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();



    public String getApplicationProperty(String propertyKey) {
        Properties props = new Properties();

        try {
            InputStream appPropFile = new FileInputStream("C:\\Users\\Lenovo\\IdeaProjects\\automationproj\\config.properties");
            props.load(appPropFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = props.getProperty(propertyKey);
        return String.valueOf(result);
    }



    public static String getenv() {

        String env = AppConstants.env;
        return String.valueOf(env);
    }

    private static String readFileAsString(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }


    public static JSONObject configJsonObject() throws JSONException, IOException {

        String filename = "config.json";
        String jsonContent = readFileAsString(filename);
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject;

    }


    public static String getUserName() throws JSONException, IOException {

        JSONObject jsonObject = configJsonObject();
        JSONObject envConfig = jsonObject.getJSONObject(getenv());
        return envConfig.getString("username");
    }

    public static String getPassword() throws JSONException, IOException {

        JSONObject jsonObject = configJsonObject();
        JSONObject envConfig = jsonObject.getJSONObject(getenv());
        return envConfig.getString("password");
    }

    public static String geturl() throws JSONException, IOException {

        JSONObject jsonObject = configJsonObject();
        JSONObject envConfig = jsonObject.getJSONObject(getenv());
        return envConfig.getString("url");
    }

    public static void testStep(String msg, int step, ThreadLocal<ExtentTest> testLogger) throws JSONException, IOException {
        testLogger.get().log(Status.INFO, "Step "+step+"- "+msg);
    }

    public static String getIPAddress() throws UnknownHostException {

        InetAddress localhost = InetAddress.getLocalHost();

        return localhost.getHostAddress();

    }

}
