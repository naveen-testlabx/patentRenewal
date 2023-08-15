package utils;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import commons.core.WebDriverFactory;
import org.openqa.selenium.WebDriver;


import java.io.IOException;

public class DriverInstance {
    /**
     * Initializes the driver instance based on the device ID
     *
     * @param browserInfo the browser info
     */
    public void setUpBrowserOnAnyGivenDevice(String browserInfo) {

        WebDriverFactory df = new WebDriverFactory();
        WebDriver driver = df.initializeWebDriver(browserInfo);
        df.setWebDriver(driver);
        ExtentCucumberAdapter.addTestStepLog(df.getWebDriverInstanceDetails(browserInfo));

        //TODO: add application version
        // ExtentCucumberAdapter.addTestStepLog("Web App verison is >  TBD " );
    }

    /**
     * Shuts down the device once the execution is done.
     *
     * @throws IOException the io exception
     */
    public void shutsDownTheDevice() {
        WebDriverFactory df = new WebDriverFactory();
        if (df.getWebDriver() != null) {
            df.getWebDriver().quit();
            df.setWebDriver(null);
        }
    }
}
