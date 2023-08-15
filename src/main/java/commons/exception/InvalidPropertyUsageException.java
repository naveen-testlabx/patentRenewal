package commons.exception;

public class InvalidPropertyUsageException extends FrameworkException {
    public InvalidPropertyUsageException(String message) {
        super(message);
    }

    public InvalidPropertyUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
