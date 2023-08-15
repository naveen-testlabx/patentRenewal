package commons.core;

import com.google.common.base.Throwables;

class ErrorDetails {

    Throwable throwable;

    ErrorDetails(Throwable throwable) {
        this.throwable = throwable;
    }

    String getExceptionClassString() {
        return throwable.getClass().getSimpleName();
    }

    String getExceptionMessage() {
        return throwable.getMessage();
    }

    String getExceptionStackTrace() {
        return Throwables.getStackTraceAsString(throwable);
    }

    Class<? extends Throwable> getExceptionClass() {
        return throwable.getClass();
    }
}
