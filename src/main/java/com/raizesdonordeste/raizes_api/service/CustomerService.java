package com.raizesdonordeste.raizes_api.service;

import com.raizesdonordeste.raizes_api.entity.Customer;
import com.raizesdonordeste.raizes_api.repository.CustomerRepository;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {

        customer.setRegistrationDate(LocalDateTime.now());

        return customerRepository.save(customer);
    }

}
