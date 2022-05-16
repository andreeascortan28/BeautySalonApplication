package org.loose.fis.bsa.model;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Reservation {


    @Id
    private NitriteId id;

    private String username;
    private String hour;
    private String date;

    private String departmentfacility;

    private String department;

    private String facility;

    private int price;

    public Reservation() {

    }

    public Reservation(String username, String departmentfacility, String date, String hour, int price) {
        this.username = username;
        this.departmentfacility = departmentfacility;
        this.date = date;
        this.hour = hour;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }



    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }

    public String getDepartmentfacility() {
        return departmentfacility;
    }

    public void setDepartmentfacility(String departmentfacility) {
        this.departmentfacility = departmentfacility;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public NitriteId getId() {
        return id;
    }
}
