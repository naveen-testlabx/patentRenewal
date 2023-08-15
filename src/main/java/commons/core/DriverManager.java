package commons.core;

import commons.enums.Browsers;
import commons.enums.PlatformType;
import commons.exception.InvalidBrowserInfoException;
import commons.exception.InvalidDeviceInfoException;
import commons.logger.Logger;
import commons.manager.web.local.ChromeManager;
import commons.manager.web.local.EdgeManager;
import commons.manager.web.local.FirefoxManager;
import commons.manager.web.remote.RemoteManagerRemote;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

import static commons.constants.FrameworkConstants.getBrowserstackUrl;

public class DriverManager {

    Properties props = new PropertyReader().getGlobalConfigProperties();
    Properties bs_props = new PropertyReader().getBrowserStackConfigProperties();
    CapabilitiesManagerImpl capsMgr = new CapabilitiesManagerImpl();
    private static final Logger LOG = new Logger(DriverManager.class.getName());


    protected RemoteWebDriver initializeBrowserstackWebDriver(String browserInfo) {
        DesiredCapabilities caps = this.getCapabilitiesForWebDriver(browserInfo.trim());
        caps.merge(capsMgr.getBrowserstackCommonCapabilities());
        caps.merge(capsMgr.getBrowserStackWebSpecificCapabilities(caps));
        return new RemoteManagerRemote().getDriver(getBrowserstackUrl(), caps);
    }

    protected RemoteWebDriver initializeSeleniumGridWebDriver(String browserInfo) {
        String url = props.getProperty("gridUrl");
        DesiredCapabilities caps = this.getCapabilitiesForWebDriver(browserInfo);
        return new RemoteManagerRemote().getDriver(url, caps);
    }

    protected WebDriver initializeChromeWebDriver(String browserInfo) {
        ChromeOptions options = new ChromeOptions();
        //TODO: Add as needed
        return new ChromeManager().getDriver(options);
    }

    protected WebDriver initializeFirefoxWebDriver(String browserInfo) {
        FirefoxOptions options = new FirefoxOptions();
        //TODO: Add as needed
        return new FirefoxManager().getDriver(options);
    }

    protected WebDriver initializeEdgeWebDriver(String browserInfo) {
        EdgeOptions options = new EdgeOptions();
        //TODO: Add as needed
        return new EdgeManager().getDriver(options);
    }

    protected WebDriver initializeSafariWebDriver(String browserInfo) {
        SafariOptions options = new SafariOptions();
        WebDriver driver = new SafariDriver(options);
        return driver;
    }
    DesiredCapabilities getCapabilitiesForWebDriver(String browserInfo) {
        try {
            int identifier = Integer.parseInt(browserInfo.toLowerCase().replaceAll("browser", ""));
            return getUserDefinedBrowserCapabilities(identifier);
        } catch (Exception e) {
            LOG.error("Unsupported browser identifier / format provided: The given value is:" + browserInfo + " whereas the supported syntax is <browser1, browser2,...>");
            throw new InvalidDeviceInfoException("Unsupported device identifier - " + browserInfo);
        }
    }

    DesiredCapabilities getCapabilitiesForAppiumDriver(String deviceID) {
        try {
            int identifier = Integer.parseInt(deviceID.toLowerCase().replaceAll("device", ""));
            return getUserDefinedAppiumCapabilities(identifier);
        } catch (Exception e) {
            LOG.error("Unsupported device identifier / format provided: The given value is:" + deviceID + " whereas the supported syntax is <device1, device2,...>");
            throw new InvalidDeviceInfoException("Unsupported device identifier - " + deviceID);
        }
    }

    private DesiredCapabilities getUserDefinedAppiumCapabilities(int identifier) {
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            PlatformType platformType = PlatformType.lookup(props.getProperty("platformName" + identifier).toUpperCase());
            caps.setCapability("platformName", platformType);
            caps.setCapability("deviceName", props.getProperty("deviceName" + identifier));
            caps.setCapability("platformVersion", props.getProperty("platformVersion" + identifier));
            return caps;
        } catch (Exception e) {
            LOG.error("Unsupported platformName provided. Valid values are: <ANDROID, IOS>");
            throw new InvalidDeviceInfoException("Unsupported platformName provided");
        }
    }

    private DesiredCapabilities getUserDefinedBrowserCapabilities(int identifier) {
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            if (!(props.getProperty("deviceName" + identifier) == null)) {
                caps.setCapability("platformName", PlatformType.lookup(props.getProperty("os" + identifier).toUpperCase()));
                caps.setCapability("deviceName", props.getProperty("deviceName" + identifier));
                caps.setCapability("platformVersion", props.getProperty("deviceVersion" + identifier));
                caps.setCapability("browserName", Browsers.lookup(props.getProperty("browser" + identifier).toUpperCase()));
            } else {
                caps.setCapability("os", PlatformType.lookup(props.getProperty("os" + identifier).toUpperCase()));
                caps.setCapability("osVersion", props.getProperty("osVersion" + identifier).toUpperCase());
                caps.setCapability("browser", Browsers.lookup(props.getProperty("browser" + identifier).toUpperCase()));
            }
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid Browser capabilities provided.!");
            throw new InvalidBrowserInfoException("Invalid Browser capabilities provided",e);
        }
        return caps;
    }
}
