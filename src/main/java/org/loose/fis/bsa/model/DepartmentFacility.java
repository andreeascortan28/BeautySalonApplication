package org.loose.fis.bsa.model;

public class DepartmentFacility {

    private String departmentfacility;

    private int price;

    public DepartmentFacility() {

    }
    public DepartmentFacility(String departmentfacility, int price) {
        this.departmentfacility = departmentfacility;
        this.price = price;
    }

    public DepartmentFacility(){};

    public String getDepartmentfacility() {
        return departmentfacility;
    }

    public void setDepartmentfacility(String departmentfacility) {
        this.departmentfacility = departmentfacility;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
