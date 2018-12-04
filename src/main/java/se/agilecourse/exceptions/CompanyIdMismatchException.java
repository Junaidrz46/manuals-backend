package se.agilecourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CompanyIdMismatchException extends RuntimeException {


    public CompanyIdMismatchException() {
        super();
    }

    public CompanyIdMismatchException(String message) {
        super(message);
    }

    public CompanyIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
