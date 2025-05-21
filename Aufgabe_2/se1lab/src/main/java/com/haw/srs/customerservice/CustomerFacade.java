package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@CrossOrigin
public class CustomerFacade {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Lecturer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/{id:[\\d]+}")
    public Lecturer getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @DeleteMapping("/{id:[\\d]+}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        Lecturer lecturer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        customerRepository.delete(lecturer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lecturer createCustomer(@RequestBody Lecturer lecturer) {

        return customerRepository.save(lecturer);
    }

    @PutMapping
    public Lecturer updateCustomer(@RequestBody Lecturer lecturer) throws CustomerNotFoundException {
        Lecturer lecturerToUpdate = customerRepository
                .findById(lecturer.getId())
                .orElseThrow(() -> new CustomerNotFoundException(lecturer.getId()));

        return customerRepository.save(lecturer);
    }
}
