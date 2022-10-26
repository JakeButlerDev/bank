package com.careerdevs.bank.repositories;

import com.careerdevs.bank.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
