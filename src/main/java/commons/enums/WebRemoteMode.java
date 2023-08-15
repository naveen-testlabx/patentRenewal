package commons.enums;

import commons.utils.LookupUtils;

public enum WebRemoteMode {

    BROWSERSTACK,
    SELENIUMGRID;

    static public WebRemoteMode lookup(String id) {
        return LookupUtils.lookup(WebRemoteMode.class, id);
    }
}
