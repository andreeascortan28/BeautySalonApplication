package org.loose.fis.bsa.exceptions;

public class MakingReservationException extends Exception {

    public MakingReservationException() {
        super(String.format("There is already an reservation at this hour!"));
    }
}
