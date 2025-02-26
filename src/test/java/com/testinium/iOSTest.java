package com.testinium;

import utils.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class iOSTest {

    public static void main(String[] args) {

        AppiumDriver iOSDriver = new DriverManager().initializeDriver("iOS", "CLI server");
        WebElement generalButton = iOSDriver.findElement(AppiumBy.accessibilityId("General"));
        generalButton.click();
        System.out.println("Clicked on Alerts Views button");
        WebDriverWait wait = new WebDriverWait(iOSDriver, Duration.ofSeconds(10));
        WebElement aboutButton = iOSDriver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='About']"));
        aboutButton.click();
        WebElement backButton = iOSDriver.findElement(AppiumBy.accessibilityId("Back"));
        backButton.click();
        WebElement backSettings = iOSDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='Settings']"));
        backSettings.click();

        fingerSwipe(iOSDriver, 100, 800, 100, 200, 1000);

        WebElement appsButton = iOSDriver.findElement(AppiumBy.accessibilityId("Apps"));
        appsButton.click();


        iOSDriver.quit();
        System.out.println("Session ended");
    }

    public static void fingerSwipe(AppiumDriver driver, int startX, int startY, int endX, int endY, long timeInMillis) {
        PointerInput touchAction = new PointerInput(PointerInput.Kind.TOUCH, "touchAction");

        Interaction moveToStart = touchAction.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY);

        Interaction pressDown = touchAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg());

        Interaction moveToEnd = touchAction.createPointerMove(Duration.ofMillis(timeInMillis), PointerInput.Origin.viewport(), endX, endY);

        Interaction pressUp = touchAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(touchAction, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);

        driver.perform(Arrays.asList(swipe));
    }



}
