package com.example.SpringAOP.model;

public class ExternalPerson {

    private String fullName;
    private int yearsOld;


    public ExternalPerson() {
    }

    public ExternalPerson(String fullName, int yearsOld) {
        this.fullName = fullName;
        this.yearsOld = yearsOld;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }
}
