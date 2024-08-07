package com.restApi.bankPortal.services;

import com.restApi.bankPortal.domain.dto.CustomerDto;
import com.restApi.bankPortal.domain.entities.CustomerEntity;

public interface CustomerService {

    public CustomerEntity createCustomer(CustomerEntity customer);
}
