package commons.logger;

import java.util.logging.Level;

import static java.util.logging.Level.*;

public class Logger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    public void error(String msg) {
        setLogLevel(SEVERE, ANSI_RED + msg + ANSI_RESET);
    }

    public void error(String msg, Throwable cause) {
        setLogLevel(SEVERE, ANSI_RED + msg + ANSI_RESET, cause);
    }

    public void info(String msg) {
        setLogLevel(INFO, ANSI_GREEN + msg + ANSI_RESET);
    }

    public void info(String msg, Throwable cause) {
        setLogLevel(INFO, ANSI_GREEN + msg + ANSI_RESET, cause);
    }

    public void warn(String msg) {
        setLogLevel(WARNING, ANSI_YELLOW + msg + ANSI_RESET);
    }

    public void warn(String msg, Throwable cause) {
        setLogLevel(WARNING, ANSI_YELLOW + msg + ANSI_RESET, cause);
    }

    public void debug(String msg) {
        setLogLevel(ALL, "[DEBUG] " + ANSI_BLUE + msg + ANSI_RESET);
    }

    public void debug(String msg, Throwable cause) {
        setLogLevel(ALL, "[DEBUG] " + ANSI_BLUE + msg + ANSI_RESET, cause);
    }

    private void setLogLevel(Level logLevel, String msg) {
        System.out.println(logLevel + ": [" + name + "] " + msg);
    }

    private void setLogLevel(Level logLevel, String msg, Throwable cause) {
        System.out.println(logLevel + ": [" + name + "] " + msg + " " + cause);
    }

}
