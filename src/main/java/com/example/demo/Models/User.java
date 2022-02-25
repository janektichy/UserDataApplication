package com.example.demo.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private long Id;

    private String name;
    private String surname;
    private boolean status;
    private String email;
    private String[] phoneNumber;
    private LocalDate dateOfCreation;

    public User(){
    }
    public User(long id, String name, String surname, boolean status, String email, String[] phoneNumber, LocalDate dateOfCreation) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfCreation = dateOfCreation;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
