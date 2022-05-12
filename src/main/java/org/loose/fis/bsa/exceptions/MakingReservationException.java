package org.loose.fis.bsa.exceptions;

public class MakingReservationException extends Exception {

    public MakingReservationException() {
        super(String.format("Exista deja o rezervare la aceasta ora!"));
    }
}
