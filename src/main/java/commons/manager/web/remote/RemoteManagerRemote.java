package commons.manager.web.remote;

import commons.exception.BrowserStackException;
import commons.exception.FrameworkException;
import commons.logger.Logger;
import commons.manager.iRemoteWebManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Responsible for all Android driver related functions
 */
public class RemoteManagerRemote implements iRemoteWebManager {
    private static final Logger LOG = new Logger(RemoteManagerRemote.class.getName());

    /**
     * Gets the Remote Web Driver instance
     *
     * @param url          - String version
     * @param capabilities
     * @return Android driver instance
     */
    public RemoteWebDriver getDriver(String url, DesiredCapabilities capabilities) {
        try {
            URL formattedUrl = new URL(url);
            return getDriver(formattedUrl, capabilities);
        } catch (MalformedURLException exc) {
            LOG.error("Format or Address of remotely/locally started server is incorrect. Given URL: " + url);
            throw new FrameworkException("Format or Address of remotely/locally started server is incorrect. Given URL: " + url + exc);
        }
    }

    /**
     * Gets the Remote Web Driver instance
     *
     * @param url
     * @param capabilities
     * @return Remote Web driver instance
     */
    public RemoteWebDriver getDriver(URL url, DesiredCapabilities capabilities) {
        try {
            System.out.println("prnting " + capabilities);
            return new RemoteWebDriver(url, capabilities);
        } catch (Exception exc) {
            if (exc.getMessage().contains("Authorization required")) {
                LOG.error("Authorization required: Check if you have set the valid credentials > " + capabilities.asMap());
                throw new BrowserStackException("Authorization required, you must provide username and access key as part of your run command");
            } else if (exc.getMessage().contains("BROWSERSTACK")) {
                LOG.error("Browserstack specific error > " + exc.getMessage());
                throw new BrowserStackException(exc.getMessage());
            } else {
                LOG.error("Session not created. Check remote server is up, devices, the server URL and capabilities. " + exc.getMessage());
                throw new FrameworkException(exc.getMessage());
            }
        }
    }


}
