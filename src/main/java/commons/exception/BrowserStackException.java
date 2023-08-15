package commons.exception;

public class BrowserStackException extends FrameworkException {
    public BrowserStackException(String message) {
        super(message);
    }

    public BrowserStackException(String message, Throwable cause) {
        super("Browserstack is unable to read your configuration. Double check your config file. " + message, cause);
    }
}
