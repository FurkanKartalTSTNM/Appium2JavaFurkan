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
    void beforeClass() {
        iOSDriver = new DriverManager().initializeDriver("iOS", "CLI server");
        selector = SelectorFactory.createElementHelper(SelectorType.IOS);
        Logger logger = LoggerFactory.getLogger(getClass());

    }

    @Test
    public void settingsTest() throws InterruptedException {
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

        // Başlangıç hareketi
        Interaction moveToStart = touchAction.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY);

        // Parmağın basılması
        Interaction pressDown = touchAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg());

        // Kaydırma hareketi
        Interaction moveToEnd = touchAction.createPointerMove(Duration.ofMillis(timeInMillis), PointerInput.Origin.viewport(), endX, endY);

        // Parmağın bırakılması
        Interaction pressUp = touchAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        // Tüm hareketlerin sıralanması
        Sequence swipe = new Sequence(touchAction, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);

        // Kaydırma işlemini gerçekleştirme
        driver.perform(Arrays.asList(swipe));
    }


}
