package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.entities.Transactions;
import com.restApi.bankPortal.repository.TransactionRepo;
import com.restApi.bankPortal.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public ApiResponse<?> transactionHistory(Long accountNo) {
        List<Transactions> transactions = transactionRepo.getAllTransactions(accountNo);

        return new ApiResponse<>(HttpStatus.OK.value(), "transactions", transactions);
    }
}

