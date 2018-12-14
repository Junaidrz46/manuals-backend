package se.agilecourse.exceptions;

import java.util.NoSuchElementException;

public class ConsumerNotFound extends NoSuchElementException {
    public ConsumerNotFound(String s) {
        super(s);
    }
}
