package commons.manager.web.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeManager {

    public WebDriver getDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    public WebDriver getDriver(EdgeOptions options) {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(options);
    }
}
