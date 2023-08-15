package commons.exception;

public class FileNotFoundFoundException extends FrameworkException {
    public FileNotFoundFoundException(String message) {
        super(message);
    }

    public FileNotFoundFoundException(String fileName, Throwable cause) {
        super("Could not read properties from file: [" + fileName + "] inside Resource folder.", cause);
    }
}
