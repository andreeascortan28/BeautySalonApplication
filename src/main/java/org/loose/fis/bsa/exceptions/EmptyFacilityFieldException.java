package org.loose.fis.bsa.exceptions;

public class EmptyFacilityFieldException extends Exception{

    public EmptyFacilityFieldException() {
        super(String.format("Please complete the facility field!"));
    }
}
