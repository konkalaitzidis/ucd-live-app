package com.example.ucdlive;

import android.icu.util.LocaleData;

import java.time.LocalDate;

public class User {

    private int ID;
    private String name;
    private String surname;
    private LocalDate birthday;

    public User(int ID, String name, String surname, LocalDate birthday) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
