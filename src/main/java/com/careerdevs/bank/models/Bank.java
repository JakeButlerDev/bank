package com.careerdevs.bank.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phoneNumber;

    public Bank(String name, String location, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    // Default constructor - not needed if there is another constructor in the class. REQUIRED BY JPA
    public Bank() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAreaCode() {
        return phoneNumber.substring(0, 3);
    }
}
