package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Customer;
import com.raizesdonordeste.raizes_api.repository.CustomerRepository;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;
import com.raizesdonordeste.raizes_api.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;
    
    // Listar todos os clientes
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    // Buscar um cliente por ID
    @GetMapping("/{id}")    
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
    // Buscar clientes por nome
    @GetMapping("/search")
    public List<Customer> searchCustomersByName(@RequestParam String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Criar um novo cliente
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Atualizar um cliente existente
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setAddress(customerDetails.getAddress());
        
        return customerRepository.save(customer);
    }

    // Deletar um cliente
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
