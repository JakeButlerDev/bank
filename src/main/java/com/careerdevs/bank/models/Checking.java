package com.careerdevs.bank.models;

import javax.persistence.*;

@Entity
public class Checking {

    @Id
    @SequenceGenerator(name = "checkingId", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkingId")
    private Long id;

    private String alias;
    private Long balance;
    private Long fee;

    public Checking() {

    }

    public Checking(String alias, Long balance, Long fee) {
        this.alias = alias;
        this.balance = balance;
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}
