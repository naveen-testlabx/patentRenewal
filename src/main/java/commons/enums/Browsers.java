package commons.enums;

import commons.utils.LookupUtils;

/**
 * Local supported browsers
 */
public enum Browsers {
    CHROME,
    SAFARI,
    EDGE,
    FIREFOX;

    static public Browsers lookup(String id) {
        return LookupUtils.lookup(Browsers.class, id);
    }
}


