package com.careerdevs.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phoneNumber;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)   // If issues, change lazy to eager
//    @JsonIncludeProperties({"firstName", "lastName", "id"})
    @JsonIgnoreProperties({"email", "age", "location", "bank"}) // Same result as commented line above - Ignore is blacklist, Include whitelist
    private List<Customer> customers;
//     Can use Sets instead of list to enforce uniqueness

    public Bank(String name, String location, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    // Default constructor - not needed if there is another constructor in the class. REQUIRED BY JPA
    public Bank() {

    }

    public List<Customer> getCustomers() {
        return customers;
    }
//
//    public void setCustomers(List<Customer> customers) {
//        this.customers = customers;
//    }

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
