package com.careerdevs.bank.repositories;

import com.careerdevs.bank.models.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Set<Checking> findAllByCustomers_Bank_id(Long bankId);
}
