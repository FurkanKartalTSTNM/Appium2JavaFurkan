package appiumTutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class iOSTest {

    public static void main(String[] args) {

        AppiumDriver iOSDriver = new DriverManager().initializeDriver("iOS", "CLI server");
        WebElement alertsViewButton = iOSDriver.findElement(AppiumBy.accessibilityId("Alert Views"));
        alertsViewButton.click();
        System.out.println("Clicked on Alerts Views button");
        iOSDriver.quit();
        System.out.println("Session ended");
    }
}
