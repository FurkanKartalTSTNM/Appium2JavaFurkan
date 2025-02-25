package tests.driver;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.util.Constants;
import tests.util.TestiniumEnvironment;

import java.net.URL;

import static tests.util.Constants.DEFAULT_PROFILE;
import static tests.util.Constants.UDID;
import static tests.util.DeviceParkUtil.setDeviceParkOptions;
import static tests.util.MediaUtil.*;


public class TestiniumIOSDriver extends IOSDriver implements CanRecordScreen {


    public TestiniumIOSDriver(URL hubUrl, DesiredCapabilities capabilities) throws Exception {
        super(new TestiniumCommandExecutor(hubUrl), overrideCapabilities(capabilities));
        tests.driver.TestiniumDriver.registerDriver(this.getSessionId(), this);
        if (recordingAllowed()){
            startScreenRecordingForIOS(this.getRemoteAddress(),this.getSessionId());
        }
    }

    private static DesiredCapabilities overrideCapabilities(DesiredCapabilities capabilities) {
        if (!DEFAULT_PROFILE.equals(TestiniumEnvironment.profile)) {
            return capabilities;
        }
        DesiredCapabilities overridden = new DesiredCapabilities(capabilities);
        overridden.setCapability(Constants.PLATFORM_NAME, Platform.IOS);
        overridden.setCapability(UDID, TestiniumEnvironment.udid);
        overridden.setCapability("appium:automationName", "XCUITest");
        overridden.setCapability("appium:bundleId", TestiniumEnvironment.bundleId);
        capabilities.setCapability("app", TestiniumEnvironment.app);
        overridden.setCapability("appium:autoAcceptAlerts", true);
        setDeviceParkOptions(overridden);

        System.out.println("deneme"+overridden);

        return overridden;
    }



    @Override
    public void quit() {
        try {
            stopScreenRecordingForIOS(this.getRemoteAddress(), String.valueOf(this.getSessionId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TestiniumDriver.postQuit(this);
        super.quit();
    }
}
