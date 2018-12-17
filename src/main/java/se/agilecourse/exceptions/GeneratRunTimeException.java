package se.agilecourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class GeneratRunTimeException extends RuntimeException {
    public GeneratRunTimeException() {
    }

    public GeneratRunTimeException(String message) {
        super(message);
    }

    public GeneratRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratRunTimeException(Throwable cause) {
        super(cause);
    }

    public GeneratRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
