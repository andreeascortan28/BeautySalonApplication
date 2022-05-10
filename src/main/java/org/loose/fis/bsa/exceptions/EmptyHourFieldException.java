package org.loose.fis.bsa.exceptions;

public class EmptyHourFieldException extends Exception {

    public EmptyHourFieldException() {
        super(String.format("Please complete the hour field!"));
    }
}
