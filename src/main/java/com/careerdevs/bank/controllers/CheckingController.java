package com.careerdevs.bank.controllers;

import com.careerdevs.bank.models.Checking;
import com.careerdevs.bank.models.Customer;
import com.careerdevs.bank.repositories.CheckingRepository;
import com.careerdevs.bank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

//@CrossOrigin
@RestController
@RequestMapping("/api/checking")
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    CustomerRepository customerRepository;

    // Down the road if you want to authenticate this person's login you could make sure the route is authenticated
    @PostMapping("/{id}")
    public ResponseEntity<?> createOneCheckingAccount(@RequestBody Checking newCheckingAcct, @PathVariable Long customerId) {
        // Find the customer in customer database
        // Return bad request if no customer
        // Add customer record to newCheckingAcct object
        // Save object

        Customer requestedCustomer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );
        newCheckingAcct.getCustomers().add(requestedCustomer);

        Checking addedCheckingAcct = checkingRepository.save(newCheckingAcct);
        return new ResponseEntity<>(addedCheckingAcct, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCheckingAccounts() {
        List<Checking> allCheckingAccounts = checkingRepository.findAll();
        return new ResponseEntity<>(allCheckingAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCheckingAccountById(@PathVariable Long id) {
        Checking foundCheckingAccount = checkingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(foundCheckingAccount, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCheckingAccount(@PathVariable Long id, @RequestBody Checking newCheckingData) {
        Checking requestedCheckingAccount = checkingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (!newCheckingData.getAlias().equals("")) {
            requestedCheckingAccount.setAlias(newCheckingData.getAlias());
        }
        return new ResponseEntity<>(checkingRepository.save(requestedCheckingAccount), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCheckingAccount(@PathVariable Long id) {
        Checking requestedCheckingAccount = checkingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        checkingRepository.delete(requestedCheckingAccount);
        return ResponseEntity.ok(requestedCheckingAccount);
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<?> getAllAccountsByBankId(@PathVariable("id") Long bankId) {
        Set<Checking> foundCheckingAccounts =  checkingRepository.findAllByCustomers_Bank_id(bankId);
        return new ResponseEntity<>(foundCheckingAccounts, HttpStatus.OK);
    }

    @PostMapping("/addcustomer/{custId}/{accountId}")
    public ResponseEntity<?> addCustomerToCheckingAccount(@PathVariable Long custId, @PathVariable Long accountId) {
        // Find account or return 404
        // Find customer or return 404
        // Add customer to account's customer list
        Customer addedCustomer = customerRepository.findById(custId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Checking foundAccount = checkingRepository.findById(accountId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        foundAccount.getCustomers().add(addedCustomer);
        return new ResponseEntity<>(checkingRepository.save(foundAccount), HttpStatus.OK);
    }
}
