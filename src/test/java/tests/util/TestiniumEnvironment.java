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
    public static URL hubURL;



    public void init() {
        profile = "testinium";
        String envProfile = System.getenv("profile");
        envProfile="testinium";
        sessionId = System.getProperty("sessionId");
        System.out.println("sessionId:"+ sessionId);

        if (envProfile.equals("testinium")) {
            sessionId = System.getenv("deviceParkSessionId") != null ? System.getenv("sessionId") : "ad70ba7e-1b42-470f-9393-035cdd7569e6";
            appiumVersion = System.getenv("appiumVersion") != null ? System.getenv("appiumVersion") : "2.5.4";
            takeScreenRecording = System.getenv("takeScreenRecording") != null ? System.getenv("takeScreenRecording") : "true";
            app = System.getenv("app") != null ? System.getenv("app") : "null";
            udid = System.getenv("udid") != null ? System.getenv("udid") : "f57820360927d404db9f5147acae9f02a5518fc6";
            String hubUrlString = System.getenv("hubURL") != null ? System.getenv("hubURL") : "http://localhost:4723";
            try {
                hubURL = new URL(hubUrlString);
            } catch (Exception e) {
                throw new RuntimeException("Geçersiz hubURL: " + hubUrlString, e);
            }




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
