package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class DriverManager {

    public AppiumDriver initializeDriver(String platformName, String appiumServerChoice) {
        AppiumDriver appiumDriver = null;
        URL url = null;
        String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "ApiDemos-debug.apk";

        String iOSAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "UIKitCatalog.app";

        if (appiumServerChoice.equals("CLI server")) {
            try {
                url = new URL("http://192.168.1.167:4723/");
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
            case "Android" -> {
                UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
                uiAutomator2Options.setPlatformName("Android")
                        .setAutomationName("UiAutomator2")
                        .setAvd("Pixel_8_Pro")
                        .setApp(androidAppPath)
                        .setAvdLaunchTimeout(Duration.ofMinutes(5))
                        .setNewCommandTimeout(Duration.ofMinutes(2));
                return new AndroidDriver(url, uiAutomator2Options);
            }
            case "iOS" -> {
                XCUITestOptions xcuiTestOptions = new XCUITestOptions();
                HashMap<String, Object> deviceParkOptions = new HashMap<>();
                deviceParkOptions.put("sessionId", "fa822b91-012e-4f28-9de3-3e3322d03bfb");
                deviceParkOptions.put("appiumVersion", "2.5.4");
                xcuiTestOptions.setCapability("dp:options",deviceParkOptions);


                xcuiTestOptions.setPlatformName("iOS")
                        .setAutomationName("XCUITest")
                        .setUdid("723DDD46-03E1-488B-860B-7AAF64EC44E1")
                        .setBundleId("com.apple.Preferences");
                return new IOSDriver(url, xcuiTestOptions);
            }
            default -> Assert.fail("Invalid platform name is passed. Please pass either 'Android' or 'iOS'");
        }
        return appiumDriver;
    }
}
