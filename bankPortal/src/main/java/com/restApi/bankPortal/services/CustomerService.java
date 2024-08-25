package com.restApi.bankPortal.services;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.LoginForm;
import com.restApi.bankPortal.domain.entities.CustomerEntity;

import java.util.Optional;

public interface CustomerService {

    CustomerEntity createCustomer(CustomerEntity customer);
    Optional<CustomerEntity> findOne(Long id);
    boolean existByEmail(String email);
    CustomerEntity findByEmail(String email);

    ApiResponse<?> authenticate(LoginForm request);
}
