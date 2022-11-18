package com.careerdevs.bank.repositories;

import com.careerdevs.bank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByLastName(String lastName);

    List<Customer> findAllByBank_id(Long id);

    // find by user's id
    Optional<Customer> findByUser_username(String username);

}
