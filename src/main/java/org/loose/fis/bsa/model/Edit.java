package org.loose.fis.bsa.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Edit {

    private SimpleStringProperty Facility;
    private SimpleIntegerProperty Price;


    public Edit(String Facility, Integer Price){
        this.Facility = new SimpleStringProperty(Facility);
        this.Price = new SimpleIntegerProperty(Price);
    }

    public void setFacility(String Facility){
        this.Facility = new SimpleStringProperty(Facility);
    }
    public String getFacility(){
        return Facility.get();
    }

    public void setPrice(Integer Pret){
        this.Price = new SimpleIntegerProperty(Pret);
    }

    public Integer getPrice(){
        return Price.get();
    }
}