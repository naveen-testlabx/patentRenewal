package commons.core;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface CapabilitiesManager {
    DesiredCapabilities getBrowserstackCommonCapabilities();

    DesiredCapabilities getBrowserStackAppiumSpecificCapabilities(DesiredCapabilities capabilities);

}
