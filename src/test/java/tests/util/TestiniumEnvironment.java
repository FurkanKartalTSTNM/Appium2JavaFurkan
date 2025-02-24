package tests.util;

public class TestiniumEnvironment {

    public static String sessionId;
    public static String appiumVersion;
    public static String profile;
    public static String takeScreenRecording;
    public static String platform;
    public static String app;
    public static String udid;
    public static String takeScreenshot;
    public static String appPackage;
    public static String appActivity;
    public static String bundleId;
    public static String hubUrl;



    public void init() {
        profile = "testinium";
        String envProfile = System.getenv("profile");
        envProfile="testinium";
        sessionId = System.getProperty("sessionId");
        System.out.println("sessionId:"+ sessionId);

        if (envProfile.equals("testinium")) {
            sessionId = System.getProperty("sessionId") != null ? System.getProperty("sessionId") : "b044c078-d8f4-4a9e-bcbe-ff8ad92fcd87";
            appiumVersion = System.getProperty("appiumVersion") != null ? System.getProperty("appiumVersion") : "2.5.4";
            takeScreenRecording = System.getProperty("takeScreenRecording") != null ? System.getProperty("takeScreenRecording") : "true";
            takeScreenshot = System.getProperty("takeScreenshot") != null ? System.getProperty("takeScreenRecording") : "true";
            app = System.getProperty("app") != null ? System.getProperty("app") : "null";
            udid = System.getProperty("udid") != null ? System.getProperty("udid") : "null";
            appPackage = System.getProperty("appPackage") != null ? System.getProperty("appPackage") : "null";
            appActivity = System.getProperty("appActivity") != null ? System.getProperty("appActivity") : "null";
            bundleId = System.getProperty("bundleId") != null ? System.getProperty("bundleId") : "null";
            hubUrl = System.getProperty("hubUrl");


            System.out.println("sessionId:" + sessionId );
            System.out.println("appiumVersion:" + appiumVersion);
            System.out.println("takeScreenRecording:" + takeScreenRecording);
            System.out.println("takeScreenshot:" + takeScreenshot);
            System.out.println("app:" + app);
            System.out.println("udid:" + udid);
            System.out.println("appPackage:" + appPackage);
            System.out.println("appActivity:" + appActivity);
            System.out.println("bundleId:" + bundleId);
            System.out.println("hubUrl:" + hubUrl);






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
