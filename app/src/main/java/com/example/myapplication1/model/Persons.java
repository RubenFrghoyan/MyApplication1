package com.example.myapplication1.model;

import android.view.View;

public class Persons {

    public String name;
    public String surname;
    public  int iD;

    public Persons(String name, String surname, int iD) {
        this.name = name;
        this.surname = surname;
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
}
