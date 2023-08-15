package commons.enums;

import commons.utils.LookupUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of the platform types used for execution.
 */
public enum PlatformType {
    ANDROID("Android"),
    IOS("iOS"),
    WINDOWS("Windows"),
    MAC("OS X");

    final String value;

    PlatformType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String printAll() {
        return Stream.of(PlatformType.values()).
                map(PlatformType::name).
                collect(Collectors.joining(", "));
    }
    static public PlatformType lookup(String id) {
        return LookupUtils.lookup(PlatformType.class, id);
    }
}
