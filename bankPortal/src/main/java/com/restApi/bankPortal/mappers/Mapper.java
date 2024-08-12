package com.restApi.bankPortal.mappers;

import com.restApi.bankPortal.domain.entities.CustomerEntity;

import java.util.Optional;

public interface Mapper<A, B> {

    B toDto(CustomerEntity a);
    A toEntity(B b);
}
