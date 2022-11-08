package com.careerdevs.bank.controllers;

import com.careerdevs.bank.models.Bank;
import com.careerdevs.bank.models.Customer;
import com.careerdevs.bank.repositories.BankRepository;
import com.careerdevs.bank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.CipherSpi;
import java.util.List;
import java.util.Optional;

//@CrossOrigin()
@RestController
@RequestMapping ("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankRepository bankRepository;

    @PostMapping ("/{bankId}")
    public ResponseEntity<Customer> createOneCustomer(@RequestBody Customer newCustomerData, @PathVariable Long bankId) {
        // Before we save customer data, need to find bank by id in repository
        // If bank does not exist, return 400 - Bad Request
        // If bank exists, add to new customer data and save
        Bank foundBank = bankRepository.findById(bankId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );

        newCustomerData.setBank(foundBank);
        Customer newCustomer = customerRepository.save(newCustomerData);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping ("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {
        customerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: Currently updates or creates, change to only update
    @PutMapping ("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Customer>> getOneByLastName(@PathVariable String lastName) {
        List<Customer> foundCustomer = customerRepository.findAllByLastName(lastName);

        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping("/bank/{bankId}")
    public ResponseEntity<List<Customer>> getAllCustomersByBankId(@PathVariable Long bankId) {
        List<Customer> customers = customerRepository.findAllByBank_id(bankId);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
