package org.loose.fis.bsa.exceptions;

public class NotFreeWindowException extends Exception {

    public NotFreeWindowException() {
        super(String.format("There is already a reservation made at this hour!"));
    }
}
