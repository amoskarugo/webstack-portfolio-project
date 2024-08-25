package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.domain.entities.Transactions;
import com.restApi.bankPortal.repository.TransactionRepo;
import com.restApi.bankPortal.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public Optional<Transactions> findByID(Long id) {
        return transactionRepo.findById(id);
    }

    @Override
    public Transactions updateTransaction(Transactions transaction) {
        return transactionRepo.save(transaction);
    }
}
