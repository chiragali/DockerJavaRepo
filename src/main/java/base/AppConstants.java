package base;

import org.json.JSONException;
import utils.FileUtils;

import java.io.IOException;
import java.net.UnknownHostException;

public class AppConstants {

    public static final String browserName = System.getProperty("browserName", "chrome");
    public static final String platform = System.getProperty("platform", "remote_git");

    public static final String env = System.getProperty("env", "dev");

    static String ipAddress;
    static {
        try {
            ipAddress = FileUtils.getIPAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String ipAddressUrl = "http://"+ipAddress+":4444/wd/hub";

}
