package commons.manager.web.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeManager  {

    public WebDriver getDriver( ) {
        WebDriverManager.chromiumdriver().setup();
        return new ChromeDriver();
    }

    public WebDriver getDriver(ChromeOptions options) {
        WebDriverManager.chromiumdriver().setup();
        return new ChromeDriver(options);
    }
}
