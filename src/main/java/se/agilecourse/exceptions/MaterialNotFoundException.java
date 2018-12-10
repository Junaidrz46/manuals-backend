package se.agilecourse.exceptions;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException() {
    }

    public MaterialNotFoundException(String message) {
        super(message);
    }

    public MaterialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaterialNotFoundException(Throwable cause) {
        super(cause);
    }

    public MaterialNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
