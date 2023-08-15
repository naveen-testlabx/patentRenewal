package commons.exception;

public class InvalidDeviceInfoException extends FrameworkException {
    public InvalidDeviceInfoException(String message) {
        super(message);
    }

    public InvalidDeviceInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
