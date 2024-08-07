package com.restApi.bankPortal.mappers;

public interface Mapper<A, B> {

    A toDto(B b);
    B fromDto(A a);
}
