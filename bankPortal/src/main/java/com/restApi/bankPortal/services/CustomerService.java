package com.restApi.bankPortal.services;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.entities.CustomerEntity;

import java.util.Optional;

public interface CustomerService {

    public CustomerEntity createCustomer(CustomerEntity customer);
    public Optional<CustomerEntity> findOne(Long id);
    public boolean existByEmail(String email);
    public Optional<CustomerEntity> findByEmail(String email);

    ApiResponse<?> authenticate(String username);
}
