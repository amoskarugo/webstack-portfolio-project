package com.restApi.bankPortal.mappers;

import com.restApi.bankPortal.domain.entities.CustomerEntity;


public interface Mapper<A, B> {

    B toDto(A a);
    A toEntity(B b);
}
