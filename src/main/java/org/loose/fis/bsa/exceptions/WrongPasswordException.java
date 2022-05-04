package org.loose.fis.bsa.exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
        super(String.format("You entered a wrong password!"));
    }

}
