package commons.exception;

public class InvalidBrowserInfoException extends FrameworkException {
    public InvalidBrowserInfoException(String message) {
        super(message);
    }

    public InvalidBrowserInfoException(String message, Throwable cause) {
        super(message, cause);
    }

}
