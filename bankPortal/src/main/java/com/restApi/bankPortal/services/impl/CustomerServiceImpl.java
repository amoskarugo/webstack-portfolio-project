package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.repository.CustomerRepository;
import com.restApi.bankPortal.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<CustomerEntity> findOne(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
