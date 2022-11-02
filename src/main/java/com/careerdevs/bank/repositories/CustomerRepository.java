package com.careerdevs.bank.repositories;

import com.careerdevs.bank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLastName(String lastName);
}
