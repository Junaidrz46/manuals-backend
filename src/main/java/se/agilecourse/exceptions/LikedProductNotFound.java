package se.agilecourse.exceptions;

public class LikedProductNotFound extends RuntimeException {
    public LikedProductNotFound() {
    }

    public LikedProductNotFound(String message) {
        super(message);
    }

    public LikedProductNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public LikedProductNotFound(Throwable cause) {
        super(cause);
    }

    public LikedProductNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
