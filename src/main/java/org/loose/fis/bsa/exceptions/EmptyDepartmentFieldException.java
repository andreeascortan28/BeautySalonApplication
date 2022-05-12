package org.loose.fis.bsa.exceptions;

public class EmptyDepartmentFieldException extends Exception {

    public EmptyDepartmentFieldException() {
        super(String.format("Please complete the department field!"));
    }
}
