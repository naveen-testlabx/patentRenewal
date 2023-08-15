package commons.core;

import commons.exception.FileNotFoundFoundException;
import commons.exception.FrameworkException;
import commons.logger.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Responsible for reading the config property file
 */
public class PropertyReader {
    private static Properties config_props = new Properties();
    private static Properties bs_config_props = new Properties();
    private static final Logger LOG = new Logger(PropertyReader.class.getName());


    public Properties getGlobalConfigProperties() {
        String fileName = "config.properties";
        config_props = getAnyProperties(fileName);
        return config_props;
    }

    protected Properties getBrowserStackConfigProperties() {
        String fileName = "browserstack.properties";
        bs_config_props = getAnyProperties(fileName);
        return bs_config_props;
    }

    static Properties getAnyProperties(String fileName) {
        Properties props = new Properties();
        try (InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
            props.load(is);
            return props;
        } catch (Exception e) {
            LOG.error(fileName + " failed to load. Have you changed the name or moved somewhere? Well, File MUST be at: .\\src\\main\\java\\resources\\" + fileName);
            throw new FileNotFoundFoundException(fileName, e);
        }
    }

    public static String getConfigProperty(String key) {
        String value = config_props.getProperty(key);
        if (value == null) {
            LOG.warn("Missing  value for property: " + key + ". Set to blank. REMEMBER, its Case Sensitive! ");
            return "";
        } else {
            LOG.info("Value for property: " + key + " is:" + value);
            return value;
        }
    }

    public static String getAnyBrowserStackConfigProperty(String key) {
        String value = bs_config_props.getProperty(key);
        if (value == null) {
            LOG.warn("Missing  value for property: " + key + ". Set to blank. REMEMBER, its Case Sensitive! ");
            return "";
        } else {
            LOG.info("Value for property: " + key + " is:" + value);
            return value;
        }
    }

    private static Map<String, String> asMap(Properties prop) {
        HashMap<String, String> map = new HashMap<>();
        prop.forEach((k, v) -> map.put((String) k, (String) v));
        return map;
    }

    public static long getTimeOutInSeconds() {
        try {
            return Long.parseLong(config_props.getProperty("timeOutInSeconds"));
        } catch (NumberFormatException e) {
            LOG.error("timeOutInSeconds is invalid or not provided. Please provide in config.properties", e);
            throw new FrameworkException("timeOutInSeconds is invalid or not provided", e);
        }
    }
}