package org.loose.fis.bsa.exceptions;

public class WrongRoleException extends Exception{

    public WrongRoleException() {
        super(String.format("You selected the wrong role!"));

    }


}
