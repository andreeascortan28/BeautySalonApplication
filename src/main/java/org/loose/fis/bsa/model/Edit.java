package org.loose.fis.bsa.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Edit {
    private String Facility;
    private Integer Price;

    public Edit(String Facility, Integer Price){
        this.Facility = Facility;
        this.Price = Price;
    }

    public void setFacility(String Facility){
        this.Facility = Facility;
    }
    public String getFacility(){
        return Facility;
    }

    public void setPrice(Integer Pret){
        this.Price = Pret;
    }

    public Integer getPrice(){
        return Price;
    }
}
