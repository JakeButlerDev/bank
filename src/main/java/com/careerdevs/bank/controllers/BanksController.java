package com.careerdevs.bank.controllers;

import com.careerdevs.bank.models.Bank;
import com.careerdevs.bank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/api/banks")   // Typically add /api in front of anything that returns JSON data (not webpages) as a note to other devs
public class BanksController {

    @Autowired
    private BankRepository bankRepository;

    @PostMapping
    public ResponseEntity<?> createOneBank(@RequestBody Bank newBank) {
        try {
            // validation occurs here
            Bank addedBank = bankRepository.save(newBank);
//            return ResponseEntity.ok(addedBank);  Same line as below, system returns that it has successfully created object
            return new ResponseEntity<>(addedBank, HttpStatus.CREATED);     // Status code 201
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());  // Server side error is in 500s
        }
    }

    @GetMapping ("/")
    public ResponseEntity<?> getAllBanks() {

        List<Bank> allBanks = bankRepository.findAll();
//        return new ResponseEntity<>(allBanks, HttpStatus.OK);
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);    // Alternate option to move inline

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBankById(@PathVariable Long id) {

//        Option 1: Do not need a try catch due to the throw method used below
        Bank requestedBank = bankRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));  // 404 Error, Resource not found
        return new ResponseEntity<>(requestedBank, HttpStatus.OK);


        // Option 2:
//        Optional<Bank> requestedBank = bankRepository.findById(id);
//        if (requestedBank.isEmpty()) { return new ResponseEntity<>("Invalid ID", HttpStatus.NOT_FOUND);}
//        // Line below will not run if conditional above is true, break occurs after return above
//        return new ResponseEntity<>(requestedBank.get(), HttpStatus.OK);

    }
}
