package com.careerdevs.bank.repositories;

import com.careerdevs.bank.models.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
