package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByLastname(String lastName) throws CustomerNotFoundException {
        return customerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new CustomerNotFoundException(lastName));
    }

    public Customer createCustomer(String firstName, String lastName, Gender gender) throws CustomerAlreadyExistingException {
        if (customerRepository.findByLastName(lastName).isPresent()) {
            throw new CustomerAlreadyExistingException(lastName);
        }

        return customerRepository.save(new Customer(firstName, lastName, gender));
    }
}
