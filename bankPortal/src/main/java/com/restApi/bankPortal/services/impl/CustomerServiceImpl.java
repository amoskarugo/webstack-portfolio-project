package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.repository.CustomerRepository;
import com.restApi.bankPortal.security.JwtService;
import com.restApi.bankPortal.services.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserDetailsService userDetailsService, JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
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

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public ApiResponse<?> authenticate(String username) {

        HashMap<String, Object> access_token = new HashMap<>();

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        String token = jwtService.generateToken(userDetails);
        access_token.put("access_toke", token);
        return new ApiResponse<>("login successful", true, access_token);
    }


}

