package com.example.emplyoeeapp;

public class EmployeeData
{
    private String firstName;
    private String lastName;
    private String designation;
    private String phone;

    @Override
    public String toString() {
        return "EmployeeData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation='" + designation + '\'' +
                ", phone=" + phone +
                '}';
    }

    public EmployeeData(String firstName, String lastName, String designation, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
