package Utilities

import com.saucelabs.common.SauceOnDemandAuthentication
import com.saucelabs.saucerest.SauceREST
import gherkin.formatter.model.Result
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * Created by mehmetgerceker on 9/20/15.
 */
public class WebDriverFactory {

    private static SauceREST client = null;
    private static String username = System.getenv("SAUCE_USERNAME") != null ? System.getenv("SAUCE_USERNAME") : "";
    private static String accesskey = System.getenv("SAUCE_ACCESS_KEY") != null ? System.getenv("SAUCE_ACCESS_KEY") : "";

    private static SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

    public static WebDriver getSauceDriver(String testName) {
        //The null check is quite pointless, but will keep here just in case getenv is returning "" not null
        String os = System.getenv("OS") != null ? System.getenv("OS") : null;
        String browser = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : null;
        String version = System.getenv("VERSION") != null ? System.getenv("VERSION") : null;
        String deviceName = System.getenv("DEVICE_NAME") != null ? System.getenv("DEVICE_NAME") : null;
        String deviceOrientation = System.getenv("DEVICE_ORIENTATION") != null ? System.getenv("DEVICE_ORIENTATION") : null;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);

        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability('name', testName);
        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                        "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        return driver;
    }

    public static WebDriver getSauceDriver(String browser, String version, String os, String testName) {
        //The null check is quite pointless, but will keep here just in case getenv is returning "" not null
        String deviceName = System.getenv("DEVICE_NAME") != null ? System.getenv("DEVICE_NAME") : null;
        String deviceOrientation = System.getenv("DEVICE_ORIENTATION") != null ? System.getenv("DEVICE_ORIENTATION") : null;
        String buildTag =
                System.getenv("SAUCE_BAMBOO_BUILDNUMBER") != null ? System.getenv("SAUCE_BAMBOO_BUILDNUMBER") : null;
        System.out.println("Build tag: " + buildTag);
        String seHost = System.getenv("SELENIUM_HOST") != null ? System.getenv("SELENIUM_HOST") : "ondemand.saucelabs.com";
        String sePort = "80";
        if (seHost != "ondemand.saucelabs.com") {
            sePort = System.getenv("SELENIUM_PORT") != null ? System.getenv("SELENIUM_PORT") : "80";
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);
        if (buildTag != null) capabilities.setCapability("build", buildTag);

        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability('name', testName);
        System.out.println("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                "@" + seHost + ":" + sePort + "/wd/hub");
        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                        "@" + seHost + ":" + sePort + "/wd/hub"),
                capabilities);
        return driver;
    }

    public static SauceREST getSauceRESTClient(){
        if (client == null){
            client = new SauceREST(username, accesskey);
        }
        return client;
    }

    public static void markPassFail(String sessionId, ArrayList<Result> results){
        Map<String, Object> jobInfo = new HashMap<String, Object>();
        boolean result;
        for(Result r:results){
            result = r.status == "passed";
            if (!result)
                break;
        }
        jobInfo.put('passed', result);
        getSauceRESTClient().updateJobInfo(sessionId, jobInfo);

   }
}