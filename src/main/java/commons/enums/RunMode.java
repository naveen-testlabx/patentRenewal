package commons.enums;

import commons.utils.LookupUtils;

public enum RunMode {
    REMOTE,
    LOCAL;

    static public RunMode lookup(String id) {
        return LookupUtils.lookup(RunMode.class, id);
    }
}

