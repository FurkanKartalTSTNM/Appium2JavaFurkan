package tests.util;

import java.net.URL;

public class TestiniumEnvironment {

    public static String sessionId;
    public static String appiumVersion;
    public static String profile;
    public static String takeScreenRecording;
    public static String platform;
    public static String app;
    public static String udid;
    public static String takeScreenshot;

    public static URL hubURL;



    public void init() {
        profile = "testinium";
        String envProfile = System.getenv("profile");
        envProfile="testinium";
        takeScreenRecording = System.getProperty("takeScreenRecording");
        System.out.println("takeScreenRecording:"+ takeScreenRecording);

        if (envProfile.equals("testinium")) {
            sessionId = System.getProperty("sessionId") != null ? System.getProperty("sessionId") : "ad70ba7e-1b42-470f-9393-035cdd7569e6";
            System.out.println("platform:"+ System.getenv("platform"));
            appiumVersion = System.getProperty("appiumVersion") != null ? System.getProperty("appiumVersion") : "2.5.4";
            takeScreenRecording = System.getProperty("takeScreenRecording") != null ? System.getProperty("takeScreenRecording") : "true";
            takeScreenshot = System.getProperty("takeScreenshot") != null ? System.getProperty("takeScreenshot") : "true";

            app = System.getProperty("app") != null ? System.getenv("app") : "null";
            udid = System.getProperty("udid") != null ? System.getenv("udid") : "f57820360927d404db9f5147acae9f02a5518fc6";
            String hubUrlString = System.getProperty("hubURL") != null ? System.getProperty("hubURL") : "http://localhost:4723";


            System.out.println("sessionId:" +System.getProperty("sessionId") );
            System.out.println("appiumVersion:" +System.getProperty("appiumVersion") );
            System.out.println("takeScreenRecording:" +System.getProperty("takeScreenRecording") );
            System.out.println("app:" +System.getProperty("app") );
            System.out.println("udid:" +System.getProperty("udid") );
            System.out.println("takeScreenshot:" +System.getProperty("takeScreenshot") );




        } else {
            sessionId = "a9446477-30da-4f42-833a-82de39967f97";
            appiumVersion = "2.5.4";
            takeScreenRecording = "true";
            profile = "testinium";
        }

        System.out.println("Environment Variables Initialized");
    }


    public static Boolean isPlatformAndroid() {
        platform = System.getenv("platform");
        return "Android".equals(platform);
    }


}
