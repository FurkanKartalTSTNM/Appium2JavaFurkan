package tests.util;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static tests.util.Constants.EnvironmentConstants.*;


public class DeviceParkUtil {

    /**
     * Sets device park options from environment variables
     * @param capabilities
     */
    public static void setDeviceParkOptions(DesiredCapabilities capabilities) {
        HashMap<String, Object> deviceParkOptions = new HashMap<>();
        System.out.println("appiumVersion: " + TestiniumEnvironment.appiumVersion);
        System.out.println("SessionId: " + TestiniumEnvironment.sessionId);
        deviceParkOptions.put(SESSION_ID, TestiniumEnvironment.sessionId);
        deviceParkOptions.put(APPIUM_VERSION, TestiniumEnvironment.appiumVersion);
        capabilities.setCapability(DP_OPTIONS, deviceParkOptions);
    }
}
