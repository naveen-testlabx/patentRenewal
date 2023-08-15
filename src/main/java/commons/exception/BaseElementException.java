package commons.exception;

public class BaseElementException extends FrameworkException {
    public BaseElementException(String message) {
        super(message);
    }

    public BaseElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
