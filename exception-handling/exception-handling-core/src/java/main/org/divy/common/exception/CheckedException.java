package org.divy.common.exception;

public class CheckedException extends Exception {
    private String errorCode;
    public CheckedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CheckedException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CheckedException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CheckedException(String message, String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
