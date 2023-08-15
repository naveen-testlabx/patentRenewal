package commons.core;


import commons.constants.FrameworkConstants;
import commons.enums.Browsers;
import commons.enums.RunMode;
import commons.enums.WebRemoteMode;
import commons.exception.FrameworkException;
import commons.logger.Logger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static commons.enums.RunMode.LOCAL;
import static commons.enums.RunMode.REMOTE;

/**
 * Responsible for all driver methods
 */
public class WebDriverFactory {
    private static ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Logger LOG = new Logger(WebDriverFactory.class.getName());

    Properties props;
    CapabilitiesManagerImpl capsMgr;
    commons.core.DriverManager df = new DriverManager();

    String appUrl;

    public WebDriverFactory() {
        this.props = new PropertyReader().getGlobalConfigProperties();
        this.capsMgr = new CapabilitiesManagerImpl();
    }

    public WebDriverFactory(Properties props, CapabilitiesManagerImpl capsMgr) {
        this.props = props;
        this.capsMgr = capsMgr;
    }

    public String getAppiumDriverInstanceDetails(AppiumDriver<?> driver) {
        String deviceInfo;
        deviceInfo = "DEVICE_NAME - " + driver.getCapabilities().getCapability("device")
                + "\n" + "OS_Version - " + driver.getCapabilities().getCapability("os_version")
                + "\n" + "PLATFORM - " + driver.getCapabilities().getCapability("platform").toString();
        return deviceInfo.toUpperCase();
    }

    public String getWebDriverInstanceDetails(WebDriver driver) {
        //TODO implement this
        return "";
    }

    public WebDriver getWebDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }

    public void setWebDriver(WebDriver driver) {
        WEB_DRIVER_THREAD_LOCAL.set(driver);
    }

    public WebDriver initializeWebDriver(String browserInfo) {
        try {
            WebDriver driver = null;
             appUrl = this.getEnvironmentUrl();
            RunMode runModeWeb = RunMode.lookup(props.getProperty("runModeWeb").toUpperCase());
            if (runModeWeb.equals(REMOTE)) {
                WebRemoteMode remoteModeBrowser = WebRemoteMode.lookup(props.getProperty("remoteModeWeb").toUpperCase());
                switch (remoteModeBrowser) {
                    case BROWSERSTACK:
                        driver = df.initializeBrowserstackWebDriver(browserInfo);
                        break;
                    case SELENIUMGRID:
                        driver = df.initializeSeleniumGridWebDriver(browserInfo);
                        break;
                    default:
                        throw new FrameworkException("Unsupported web remote mode!");
                }

            } else if (runModeWeb.equals(LOCAL)) {
               //Browsers browser = Browsers.lookup(props.getProperty("browser").toUpperCase());
                Browsers browser = Browsers.valueOf(browserInfo.toUpperCase());
                        switch (browser) {
                    case CHROME:
                        driver = df.initializeChromeWebDriver(browserInfo);
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--ignore-certificate-errors");
                        break;
                    case EDGE:
                        driver = df.initializeEdgeWebDriver(browserInfo);
                        break;
                    case FIREFOX:
                        driver = df.initializeFirefoxWebDriver(browserInfo);
                        break;
                    case SAFARI:
                        driver = df.initializeSafariWebDriver(browserInfo);
                        break;
                    default:
                        throw new FrameworkException("Unsupported local web browser name!");
                }
            } else {
                throw new FrameworkException("Unsupported web execution mode.!");
            }

           // this.setBrowserSize(runModeWeb, driver);
            driver.get(appUrl);
            driver.manage().window().maximize();
            return driver;
        } catch (Exception ex) {
            LOG.error("Exception in initializing the web driver. Reason: " + ex);
            throw new FrameworkException("Exception in initializing the web driver", ex);
        }
    }

    private String getEnvironmentUrl() {
        String envName = props.getProperty("envName").trim();
        if (envName == null) {
            LOG.error("envName cannot be empty! no url will be opened if envName is empty");
            throw new FrameworkException("envName cannot be empty!");
        }

        LOG.debug("Trying to read environment properties from :>" + FrameworkConstants.getConfigFilePath() + envName + ".properties");
        Properties envProps = PropertyReader.getAnyProperties(envName + ".properties");
        String appUrl = envProps.getProperty("appUrl");
        if (appUrl == null) {
            LOG.error("appUrl cannot be empty in this file:>" + FrameworkConstants.getConfigFilePath() + envName + ".properties. So add appUrl property");
            throw new FrameworkException("appUrl cannot be empty!");
        }
        return appUrl;
    }

    public String getWebDriverInstanceDetails(String browserInfo) {
        RunMode runModeWeb = RunMode.lookup(props.getProperty("runModeWeb").toUpperCase());
        if (runModeWeb.equals(REMOTE)) {
            DesiredCapabilities capabilities = df.getCapabilitiesForWebDriver(browserInfo);
            return capabilities.asMap().toString();
        } else {
            return props.getProperty("browser").toUpperCase();
        }
    }
}