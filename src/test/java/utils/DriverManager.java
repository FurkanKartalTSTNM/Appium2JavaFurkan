package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

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
                url = new URL("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub");
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
            Assert.fail("!!!!! Mentioned Appium server choice is incorrect !!!!!!");
        }
        switch (platformName) {
            case "Android":
                DesiredCapabilities capabilitiesAndroid = new DesiredCapabilities();
                HashMap<String, Object> deviceParkOptions = new HashMap<>();
                deviceParkOptions.put("sessionId", "d169dfea-de8e-4b75-85fb-6ff19fc85192");
                deviceParkOptions.put("appiumVersion", "2.5.4");

                capabilitiesAndroid.setCapability("dp:options", deviceParkOptions);
                capabilitiesAndroid.setCapability("platformName", "ANDROID");
                capabilitiesAndroid.setCapability("udid", "LGH870d82f54fb");
                capabilitiesAndroid.setCapability("automationName", "UiAutomator2");
                capabilitiesAndroid.setCapability("appPackage", "com.gratis.android");
                capabilitiesAndroid.setCapability("appActivity", "com.app.gratis.ui.splash.SplashActivity");
                capabilitiesAndroid.setCapability("autoGrantPermissions", true);
                capabilitiesAndroid.setCapability("appium:newCommandTimeout", 60000);

                capabilitiesAndroid.setCapability("app", "https://gmt-spaces.ams3.cdn.digitaloceanspaces.com/documents/devicepark/Gratis-3.3.0_141.apk");

                return new AndroidDriver(url, capabilitiesAndroid);

            case "iOS":
                DesiredCapabilities capabilitiesIOS = new DesiredCapabilities();
               // HashMap<String, Object> deviceParkOptions = new HashMap<>();
               // deviceParkOptions.put("sessionId", "d169dfea-de8e-4b75-85fb-6ff19fc85192");
               // deviceParkOptions.put("appiumVersion", "2.5.4");

                //capabilitiesIOS.setCapability("dp:options", deviceParkOptions);
                capabilitiesIOS.setCapability("platformName", "iOS");
                capabilitiesIOS.setCapability("udid", "f57820360927d404db9f5147acae9f02a5518fc6");
                capabilitiesIOS.setCapability("automationName", "XCUITest");
                capabilitiesIOS.setCapability("autoAcceptAlerts", true);
                capabilitiesIOS.setCapability("bundleId", "com.pharos.Gratis");

                log.info("Creating driver");
                System.out.println("deneme");
                return new IOSDriver(url, capabilitiesIOS);

            default:
                Assert.fail("Invalid platform name is passed. Please pass either 'Android' or 'iOS'");
        }

        return appiumDriver;
    }
}