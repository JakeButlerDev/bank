package com.careerdevs.bank.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Checking {

    @Id
    @SequenceGenerator(name = "checkingId", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkingId")
    private Long id;

    private String alias;
    private Long balance;
    private Long fee;

    @ManyToMany
    @JoinTable(
            name = "customers_accounts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "checkingAccount_id")
    )
    private List<Customer> customers;

    public Checking() {

    }

    public Checking(String alias, Long balance, Long fee) {
        this.alias = alias;
        this.balance = balance;
        this.fee = fee;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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
