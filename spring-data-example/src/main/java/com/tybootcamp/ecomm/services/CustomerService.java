package com.tybootcamp.ecomm.services;

import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.exceptions.BadRequest;
import com.tybootcamp.ecomm.exceptions.EntityNotFound;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer updatedCustomer) {
        Long updatedCustomerId = updatedCustomer.getId();
        if (updatedCustomerId == null) {
            throw new BadRequest();
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(updatedCustomerId);
        Customer customerBeforeUpdate = optionalCustomer.orElseThrow(EntityNotFound::new);

        BeanUtils.copyProperties(updatedCustomer, customerBeforeUpdate);

        return customerRepository.save(customerBeforeUpdate);
    }
}
