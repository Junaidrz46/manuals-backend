package se.agilecourse.exceptions;

import java.util.NoSuchElementException;

public class LikedProductNotFound  extends NoSuchElementException {

    public LikedProductNotFound(String s) {
        super(s);
    }
}
