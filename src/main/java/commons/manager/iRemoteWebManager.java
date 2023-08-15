package commons.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface iRemoteWebManager {
    WebDriver getDriver(URL url, DesiredCapabilities capabilities);
}
