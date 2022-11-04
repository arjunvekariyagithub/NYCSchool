package com.test.nycschools.data.model;

public class School {
    public String dbn;
    public String schoolName;
    public String address;
    public String phoneNumber;

    public School(String dbn, String schoolName, String address, String phoneNumber) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
