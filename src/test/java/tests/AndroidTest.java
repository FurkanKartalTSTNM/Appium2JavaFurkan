package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;
import tests.driver.TestiniumAndroidDriver;
import tests.driver.TestiniumIOSDriver;
import tests.util.Constants;
import tests.util.TestiniumEnvironment;
import utils.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static tests.util.Constants.UDID;


public class AndroidTest {

    private static Logger logger = LoggerFactory.getLogger(AndroidTest.class);
    protected static AndroidDriver androidDriver;
    protected static IOSDriver iosDriver;
    protected static URL hubUrl;

    protected static FluentWait<AppiumDriver> appiumFluentWait;
    protected static Selector selector ;

    static Boolean DeviceAndroid =false;


    @BeforeAll
    public static void beforeClass() {
        try {
            if(DeviceAndroid || TestiniumEnvironment.isPlatformAndroid()){
                DesiredCapabilities overridden = new DesiredCapabilities();
                hubUrl = new URL("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                androidDriver = new TestiniumAndroidDriver(hubUrl, overridden);

                selector = SelectorFactory
                        .createElementHelper(SelectorType.ANDROID);

                androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                appiumFluentWait = new FluentWait<AppiumDriver>(androidDriver);


                appiumFluentWait.withTimeout(Duration.ofSeconds(8))
                        .pollingEvery(Duration.ofMillis(350))
                        .ignoring(NoSuchElementException.class);
            }
            else {
                hubUrl = new URL("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub");
                DesiredCapabilities overridden = new DesiredCapabilities();
                overridden.setCapability(Constants.PLATFORM_NAME, Platform.IOS);
                overridden.setCapability(UDID, "5ADFD78C-520D-4EB0-BCBC-E7293160659A");
                overridden.setCapability("appium:automationName", "XCUITest");
                overridden.setCapability("appium:bundleId", "com.apple.Preferences");
                overridden.setCapability("appium:autoAcceptAlerts", true);
                iosDriver = new TestiniumIOSDriver(hubUrl, overridden);


                selector = SelectorFactory
                        .createElementHelper(SelectorType.IOS);
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                appiumFluentWait = new FluentWait<AppiumDriver>(iosDriver);
                appiumFluentWait.withTimeout(Duration.ofSeconds(8))
                        .pollingEvery(Duration.ofMillis(350))
                        .ignoring(NoSuchElementException.class);
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        selector = SelectorFactory.createElementHelper(SelectorType.IOS);

    }


    @Test
    public void settingsTestAndroid() throws InterruptedException {

        WebElement generalButton = androidDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.gratis.android:id/navigation_bar_item_icon_view\"])[1]"));
        generalButton.click();
        logger.info("Clicked to HomePage");
        Thread.sleep(2000);

        WebElement kategorilerButton = androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.gratis.android:id/navigation_bar_item_small_label_view\" and @text=\"Kategoriler\"]"));
        kategorilerButton.click();
        logger.info("Clicked to Kategoriler Button");

        Thread.sleep(2000);

        WebElement makyajButton = androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.gratis.android:id/category_name\" and @text=\"Makyaj\"]"));
        makyajButton.click();
        logger.info("Clicked to Makyaj Button");

        Thread.sleep(2000);

        WebElement backButton = androidDriver.findElement(AppiumBy.id("com.gratis.android:id/btnBack"));
        backButton.click();
        logger.info("Clicked to Back Button");

        Thread.sleep(2000);

        androidDriver.quit();
        System.out.println("Session ended");
    }

    @Test
    public void iosTest() throws InterruptedException {
        fingerSwipe(iosDriver, 100, 800, 100, 600, 1000);
        Thread.sleep(2000);
        WebElement generalButton = iosDriver.findElement(AppiumBy.accessibilityId("General"));
        generalButton.click();

        WebElement settingsButton = iosDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Settings\"]"));
        settingsButton.click();
        fingerSwipe(iosDriver, 100, 800, 100, 400, 1000);


        //WebElement backButton = iosDriver.findElement(AppiumBy.accessibilityId("Back"));
        //backButton.click();


        iosDriver.quit();

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
