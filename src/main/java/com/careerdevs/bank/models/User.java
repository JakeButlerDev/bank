package com.careerdevs.bank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class User {

    @Id
//    @UniqueConstraint()   possibility to use to enforce uniqueness
    private String username;

    private String password;
    private String loginToken;

    // Typically this should only be a one directional relationship, you would not want to get user data by customer id
//    @OneToOne(mappedBy = "user")
//    private Customer customer;

    public User() {

    }

    public User(String username, String password, String loginToken) {
        this.username = username;
        this.password = password;
        this.loginToken = loginToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
