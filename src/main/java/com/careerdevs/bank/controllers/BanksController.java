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

    // Can use PutMapping OR PostMapping
    @PostMapping ("/{id}")
    public ResponseEntity<?> postOneById(@PathVariable Long id, @RequestBody Bank newBankData) {
        Bank requestedBank = bankRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Option 1     trusting no interceptor will modify data in the middle
//        return ResponseEntity.ok(bankRepository.save(newBankData));   // newBankData must contain ALL fields

        // Option 2     Allows us to only change data provided to us, validates data at same time, only changes data we allow to be changed
        if (!newBankData.getName().equals("")) {        // Key MUST exist
            requestedBank.setName(newBankData.getName());
        }
        if (newBankData.getPhoneNumber() != null && newBankData.getPhoneNumber().length() >= 3) {     // Key DOES NOT need to exist, if it does then cannot be empty
            requestedBank.setPhoneNumber(newBankData.getPhoneNumber());
        }
        return ResponseEntity.ok(bankRepository.save(requestedBank));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteOneBankById(@PathVariable Long id) {
        // If return is unwanted, the below line is negligible - deleteById only fails if void is provided and that cannot be due to path
        Bank requestedBank = bankRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        bankRepository.deleteById(id);
        return ResponseEntity.ok(requestedBank);
    }

    @GetMapping ("/name/{name}")
    public ResponseEntity<?> findOneBankByName(@PathVariable String name) {
        Bank requestedBank = bankRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(requestedBank, HttpStatus.OK);
    }

    @GetMapping ("/areaCode/{areaCode}")
    public ResponseEntity<?> findAllBanksByAreaCode(@PathVariable String areaCode) {
        return new ResponseEntity<>(bankRepository.getAllAreaCodes(areaCode), HttpStatus.OK);
    }
}
