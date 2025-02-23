package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    public AppiumDriver initializeDriver(String platformName, String appiumServerChoice) {
        AppiumDriver appiumDriver = null;
        URL url = null;
        String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "ApiDemos-debug.apk";

        String iOSAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "UIKitCatalog.app";

        if (appiumServerChoice.equals("CLI server")) {
            try {
                url = new URL("http://192.168.1.89:4723/");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (appiumServerChoice.equals("Appium Desktop Server")) {
            try {
                url = new URL("http://0.0.0.0:4723/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
           // Assert.fail("!!!!! Mentioned Appium server choice is incorrect !!!!!!");
        }
        switch (platformName) {
            case "Android" -> {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "ANDROID");
                capabilities.setCapability("udid", "R68R902ETFR");
                capabilities.setCapability("automationName", "UiAutomator2");
                capabilities.setCapability("appPackage", "com.gratis.android");
                capabilities.setCapability("appActivity", "com.app.gratis.ui.splash.SplashActivity");
                capabilities.setCapability("autoGrantPermissions", true);
                capabilities.setCapability("appium:newCommandTimeout", 60000);
                capabilities.setCapability("app", "https://gmt-spaces.ams3.cdn.digitaloceanspaces.com/documents/devicepark/Gratis-3.3.0_141.apk");

                return new AndroidDriver(url, capabilities);
            }
            case "iOS" -> {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                HashMap<String, Object> deviceParkOptions = new HashMap<>();
                deviceParkOptions.put("sessionId", "764f1110-cca4-4528-97a1-82687a6c71df");
                deviceParkOptions.put("appiumVersion", "2.5.4");

                capabilities.setCapability("dp:options", deviceParkOptions);
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("udid", "f57820360927d404db9f5147acae9f02a5518fc6");
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("autoAcceptAlerts", true);
                capabilities.setCapability("bundleId", "com.pharos.Gratis");
                // capabilities.setCapability("app", "https://testinium-dev-cloud.s3.eu-west-1.amazonaws.com/enterpriseMobileApps/3.2.15_1720_-82c49ca8.ipa");

                log.info("Creating driver");
                System.out.println("deneme");
                return new IOSDriver(url, capabilities);
            }
            default -> throw new IllegalArgumentException("Beklenmeyen platform: " + platformName);
        }
    }
}
