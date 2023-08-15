package stepDefinitions;

import commons.core.WebDriverFactory;
import commons.utils.BrowserstackUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverInstance;

/**
 * The type Hooks.
 */
public class Hooks extends DriverInstance {

    /**
     * After steps.
     *
     * @param scenario the scenario
     * @throws Exception the exception
     */
    @AfterStep
    public void afterSteps(Scenario scenario)   {
        attachScreenshot(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = new WebDriverFactory().getWebDriver();
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        BrowserstackUtil.updateTestCaseStatus(driver, status);
        shutsDownTheDevice();
    }

    private void attachScreenshot(Scenario scenario)  {
        WebDriver driver = new WebDriverFactory().getWebDriver();

        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
