package commons.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface DriverManager {
    AppiumDriver getDriver(String Url, DesiredCapabilities capabilities);

    AppiumDriver getDriver(URL url, DesiredCapabilities capabilities);
}
