package commons.core;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
    boolean success;
    String value;
    List<?> elementsList;

    List<ErrorDetails> errors = new ArrayList<>();

    public PageResult() {
        setSuccess(false);
    }

    public boolean isSuccess() {
        return success;
    }

    public List<?> getWebElementsList() {
        return elementsList;
    }

    public void setWebElementsList(List<?> elementsList) {
        this.elementsList = elementsList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ErrorDetails> getErrors() {
        return errors;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return errors.get(0).getExceptionClass();
    }

    public String getExceptionClassString() {
        return errors.get(0).getExceptionClassString();
    }

    public String getExceptionMessage() {
        return errors.get(0).getExceptionMessage();
    }

    public String getExceptionStackTrace() {
        return errors.get(0).getExceptionStackTrace();
    }

    public void addErrors(Exception exception) {
        errors.add(new ErrorDetails(exception));
    }

    public String prettyPrintErrors() {
        if (getErrors().size() > 0) {
            return String.join("\n", "ExceptionClass:" + getExceptionClassString(),
                    "ExceptionMessage:" + getExceptionMessage(), "StackTrace:" + getExceptionStackTrace());
        }
        return "";
    }
}
