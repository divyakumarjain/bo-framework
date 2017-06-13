package org.divy.common.exception;

public class UncheckedException extends RuntimeException {
    private String errorCode;
    public UncheckedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UncheckedException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public UncheckedException(String message, String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }


    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UncheckedException() {
        super();
    }

    public UncheckedException(Throwable cause) {
        super(cause);
    }
}
