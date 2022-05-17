package org.loose.fis.bsa.exceptions;

public class WrongDateException extends Exception {

    public WrongDateException() {
        super(String.format("You entered a wrong form for the date!"));
    }
}
