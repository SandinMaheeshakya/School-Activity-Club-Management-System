package com.main.projectsacms;

public class Human {
    private int NIC;
    private String name;
    private int contactNo;
    private String address;
    

    public Human(int nic, String name, int contactNo, String address, String email, String password, String studentId) {
    }

    public Human(String name, String email, String password) {
    }

    public int getNIC() {
        return NIC;
    }

    public void setNIC(int NIC) {
        this.NIC = NIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
