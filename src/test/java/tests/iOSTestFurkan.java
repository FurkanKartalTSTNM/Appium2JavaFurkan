package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.SelectorInfo;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;
import utils.DriverManager;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class iOSTestFurkan {
    AppiumDriver iOSDriver;
    protected static Selector selector ;
    protected static FluentWait<AppiumDriver> appiumFluentWait;
    Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public void beforeClass() {
        iOSDriver = new DriverManager().initializeDriver("iOS", "CLI server");
        selector = SelectorFactory.createElementHelper(SelectorType.IOS);

    }

    @Test
    public void settingsTest() throws InterruptedException {
        WebElement generalButton = iOSDriver.findElement(AppiumBy.accessibilityId("General"));
        generalButton.click();
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


    @Test
    public void gratisTestIOS() throws InterruptedException {
        WebElement dashboardIOS = iOSDriver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"81 ilde mağazadan ÜCRETSİZ TESLİMAT fırsatları\"]"));
        dashboardIOS.click();
        logger.info("Clicked dashboardIOS");
        Thread.sleep(2000);

        WebElement markalarIOS = iOSDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[contains(@name, \"Markalar\")]"));
        markalarIOS.click();
        logger.info("Clicked markalarIOS");

        Thread.sleep(2000);

        WebElement kampanyalarIOS = iOSDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[contains(@name, \"Kampanyalar\")]"));
        kampanyalarIOS.click();
        logger.info("Clicked kampanyalarIOS");

        Thread.sleep(2000);


        //fingerSwipe(iOSDriver, 100, 800, 100, 200, 1000);

        //WebElement appsButton = iOSDriver.findElement(AppiumBy.accessibilityId("Apps"));
        //appsButton.click();


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
