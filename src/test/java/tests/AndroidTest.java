package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;
import utils.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class AndroidTest {

    AppiumDriver androidDriver;
    protected static Selector selector ;
    Logger logger = LoggerFactory.getLogger(getClass());



    @BeforeClass
    public void beforeClass() {
        androidDriver = new DriverManager().initializeDriver("Android", "CLI server");
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

}
