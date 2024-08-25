package com.restApi.bankPortal.services;

import com.restApi.bankPortal.domain.entities.Transactions;

import java.util.Optional;

public interface TransactionService {

    Optional<Transactions> findByID(Long id);
    Transactions updateTransaction(Transactions transaction);
}
