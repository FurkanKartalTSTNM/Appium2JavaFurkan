package appiumTutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;


public class AndroidTest {


    public static void main(String[] args) {

        AppiumDriver androidDriver = new DriverManager().initializeDriver("Android", "CLI server");

        WebElement graphicsButton = androidDriver.findElement(AppiumBy.accessibilityId("Graphics"));
        graphicsButton.click();
        System.out.println("Clicked on Graphics button");
        androidDriver.quit();
        System.out.println("Session is ended");

    }
}
