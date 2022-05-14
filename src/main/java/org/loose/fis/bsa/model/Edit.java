package org.loose.fis.bsa.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Edit {
    private SimpleStringProperty Facility;
    private SimpleStringProperty Price;

    public Edit(String Facility, String Price){
        this.Facility = new SimpleStringProperty(Facility);
        this.Price = new SimpleStringProperty(Price);
    }

    public void setFacility(String Facility){
        this.Facility = new SimpleStringProperty(Facility);
    }
    public String getFacility(){
        return Facility.get();
    }

    public void setPrice(String Pret){
        this.Price = new SimpleStringProperty(Pret);
    }

    public String getPrice(){
        return Price.get();
    }
}
