package commons.utils;

import commons.core.PropertyReader;
import commons.core.WebDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BrowserstackUtil {
    static Properties props = new PropertyReader().getGlobalConfigProperties();
    private static boolean isBrowserstackExecution = false;


    public static void updateTestCaseStatus(WebDriver driver, String status) {
        isBrowserstackExecution = getRunModeForWeb();
        if (driver != null && isBrowserstackExecution) {
            JavascriptExecutor jse = null;
            jse = (JavascriptExecutor) new WebDriverFactory().getWebDriver();
            setTestCaseStatus(jse, status);
        }
    }

    public static void updateTestCaseStatus(WebDriver driver, String status, String reason) {
        isBrowserstackExecution = getRunModeForWeb();
        if (driver != null && isBrowserstackExecution) {
            JavascriptExecutor jse = null;
            jse = (JavascriptExecutor) new WebDriverFactory().getWebDriver();
            setTestCaseStatus(jse, status, reason);
        }
    }

      private static void setTestCaseStatus(JavascriptExecutor jse, String status, String reason) {
        if (!status.equalsIgnoreCase("PASSED")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \""+ reason + "\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \""+ reason + "\"}}");
        }
    }

    private static void setTestCaseStatus(JavascriptExecutor jse, String status) {
        if (!status.equalsIgnoreCase("PASSED")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Validation failed\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Validation successful\"}}");
        }
    }

    private static boolean getRunModeForWeb() {
        return props.getProperty("runModeWeb").equalsIgnoreCase("remote") &&
                props.getProperty("remoteModeWeb").equalsIgnoreCase("browserstack");
    }

    private static boolean getRunModeForMobile() {
        return props.getProperty("runModeMobile").equalsIgnoreCase("remote") &&
                props.getProperty("remoteModeMobile").equalsIgnoreCase("browserstack");
    }

}
