package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.LoginForm;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.repository.CustomerRepository;
import com.restApi.bankPortal.security.JwtService;
import com.restApi.bankPortal.services.CustomerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.authenticationManager = authenticationManager;
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
    public ApiResponse<?> authenticate(LoginForm request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        HashMap<String, Object> access_token = new HashMap<>();

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.username());
        String token = jwtService.generateToken(userDetails);
        access_token.put("access_toke", token);
        return new ApiResponse<>("login successful", true, access_token);
    }


}

