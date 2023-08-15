package commons.constants;

/**
 * Framework Constants holds all the constant values used within the framework. If some value needs to be changed
 * or modified often, then it should be stored in the property files
 *
 *
 */
public class FrameworkConstants {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String BROWSERSTACK_URL = "http://hub-cloud.browserstack.com/wd/hub";
    private static final String ANDROID_CHROME_BUNDLE_ID = "com.android.chrome";
    private static final String IOS_SAFARI_BUNDLE_ID = "com.apple.mobilesafari";

    public static String getConfigFilePath() {
        return RESOURCES_PATH;
    }

    public static String getBrowserstackUrl() {
        return BROWSERSTACK_URL;
    }

    public static String getAndroidChromeBundleId() {return ANDROID_CHROME_BUNDLE_ID;}

    public static String getIOSSafariBundleId() {return IOS_SAFARI_BUNDLE_ID;}
}
