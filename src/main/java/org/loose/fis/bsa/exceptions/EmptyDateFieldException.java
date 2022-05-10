package org.loose.fis.bsa.exceptions;

public class EmptyDateFieldException extends Exception {

    public EmptyDateFieldException() {
        super(String.format("Please complete the date field!"));
    }
}
