package com.careerdevs.bank.controllers;

import com.careerdevs.bank.models.Customer;
import com.careerdevs.bank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin()
@RestController
@RequestMapping ("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Customer> createOneCustomer(@RequestBody Customer newCustomerData) {
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
    public ResponseEntity<Customer> GetOneByLastName(@PathVariable String lastName) {
        Customer foundCustomer = customerRepository.findByLastName(lastName);

        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }
}
