package commons.core;


import commons.enums.PlatformType;
import commons.logger.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

/**
 * Supplies desired capabilities based on execution environment
 */
public class CapabilitiesManagerImpl implements CapabilitiesManager {
    Properties props;
    Properties bs_props;
    private static final Logger LOG = new Logger(CapabilitiesManagerImpl.class.getName());

    public CapabilitiesManagerImpl() {
        super();
        this.props = new PropertyReader().getGlobalConfigProperties();
        this.bs_props = new PropertyReader().getBrowserStackConfigProperties();
    }

    public CapabilitiesManagerImpl(Properties props) {
        super();
        this.props = props;
    }

    /**
     * Reads the Common property values from properties and sets as desired capability
     *
     * @return desiredCapabilities
     */
    public DesiredCapabilities getBrowserstackCommonCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("project", bs_props.getProperty("browserstack.project"));
        caps.setCapability("build", bs_props.getProperty("browserstack.build"));
        caps.setCapability("name", bs_props.getProperty("browserstack.name"));
        caps.setCapability("browserstack.user", System.getProperty("browserstack.username"));
        caps.setCapability("browserstack.key", System.getProperty("browserstack.accesskey"));
        return caps;
    }

    /**
     * Reads the Appium Specific property values from properties and sets as desired capability
     *
     * @return desiredCapabilities
     */
    public DesiredCapabilities getBrowserStackAppiumSpecificCapabilities(DesiredCapabilities capabilities) {
        PlatformType platformType = (PlatformType) capabilities.getCapability("platformName");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", bs_props.getProperty("browserstack.appId." + platformType));
        caps.setCapability("automationName", bs_props.getProperty("browserstack.automationName." + platformType));
        caps.setCapability("language", bs_props.getProperty("browserstack.language." + platformType));
        caps.setCapability("locale", bs_props.getProperty("browserstack.locale." + platformType));
        caps.setCapability("deviceOrientation", bs_props.getProperty("browserstack.deviceOrientation." + platformType));
        caps.setCapability("disableAnimations", bs_props.getProperty("browserstack.disableAnimations." + platformType));
        caps.setCapability("browserstack.enableBiometric", bs_props.getProperty("browserstack.enableBiometric." + platformType));
        caps.setCapability("browserstack.debug", bs_props.getProperty("browserstack.debug." + platformType));
        caps.setCapability("browserstack.local", bs_props.getProperty("browserstack.local." + platformType));
        caps.setCapability("browserstack.idleTimeout", bs_props.getProperty("browserstack.idleTimeout." + platformType));
        caps.setCapability("browserstack.acceptInsecureCerts", bs_props.getProperty("browserstack.acceptInsecureCerts." + platformType));
        caps.setCapability("browserstack.deviceLogs", bs_props.getProperty("browserstack.deviceLogs." + platformType));
        caps.setCapability("browserstack.networkLogs", bs_props.getProperty("browserstack.networkLogs." + platformType));
        caps.setCapability("browserstack.appiumLogs", bs_props.getProperty("browserstack.appiumLogs." + platformType));
        caps.setCapability("browserstack.gpsLocation", bs_props.getProperty("browserstack.gpsLocation." + platformType));
        caps.setCapability("browserstack.geoLocation", bs_props.getProperty("browserstack.geoLocation." + platformType));
        caps.setCapability("browserstack.timezone", bs_props.getProperty("browserstack.timezone." + platformType));
        caps.setCapability("browserstack.networkProfile", bs_props.getProperty("browserstack.networkProfile." + platformType));
        caps.setCapability("browserstack.customNetwork", bs_props.getProperty("browserstack.customNetwork." + platformType));
        caps.setCapability("browserstack.uploadMedia", bs_props.getProperty("browserstack.uploadMedia." + platformType));

        LOG.info("Browserstack Appium capabilities - loaded:> " + caps.asMap());
        return caps;
    }

    public DesiredCapabilities getBrowserStackWebSpecificCapabilities(DesiredCapabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();
        String platformType = "web";
        caps.setCapability("browserstack.debug", bs_props.getProperty("browserstack.debug." + platformType));
        caps.setCapability("browserstack.local", bs_props.getProperty("browserstack.local." + platformType));
        caps.setCapability("browserstack.appiumLogs", bs_props.getProperty("browserstack.appiumLogs." + platformType));
        caps.setCapability("browserstack.networkLogs", bs_props.getProperty("browserstack.networkLogs." + platformType));
        caps.setCapability("browserstack.resolution", bs_props.getProperty("browserstack.resolution." + platformType));
        LOG.info("Browserstack Appium capabilities - loaded:> " + caps.asMap());
        return caps;
    }
}
