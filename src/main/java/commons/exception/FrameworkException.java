package commons.exception;

public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
//        System.exit(-1);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
//        System.exit(-1);
    }
}
