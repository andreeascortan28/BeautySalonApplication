package org.loose.fis.bsa.exceptions;

public class AllFieldsAreMandatory extends Exception {
    public AllFieldsAreMandatory() {
            super(String.format("Please complete all data fields!"));
    }

}
