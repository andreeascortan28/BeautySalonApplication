package org.loose.fis.bsa.model;

public class Reservation {

    private String username;
    private String hour;
    private String date;
    private String department;


    public Reservation(String username, String department, String date, String hour) {
        this.username = username;
        this.department = department;
        this.date = date;
        this.hour = hour;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
