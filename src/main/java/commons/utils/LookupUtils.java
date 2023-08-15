package commons.utils;

import commons.core.CapabilitiesManagerImpl;
import commons.exception.FrameworkException;
import commons.logger.Logger;

public class LookupUtils {
    private static final Logger LOG = new Logger(CapabilitiesManagerImpl.class.getName());

    public static <E extends Enum<E>> E lookup(Class<E> e, String id) {
        E result = null;
        try {
            result = Enum.valueOf(e, id.toUpperCase());
        } catch (IllegalArgumentException iAE) {
            LOG.error(String.format("Enum Lookup - Invalid or not supported value for enum %s : The value given: %s", e.getSimpleName(), id));
            throw new FrameworkException(String.format("%s - Invalid or not supported. Value given> %s", e.getSimpleName(), id));
        }
        return result;
    }
}
