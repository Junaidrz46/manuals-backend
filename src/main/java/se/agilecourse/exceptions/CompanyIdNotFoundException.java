package se.agilecourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CompanyIdNotFoundException extends RuntimeException {

    public CompanyIdNotFoundException() {
    }

    public CompanyIdNotFoundException(String message) {
        super(message);
    }

    public CompanyIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyIdNotFoundException(Throwable cause) {
        super(cause);
    }

    public CompanyIdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
