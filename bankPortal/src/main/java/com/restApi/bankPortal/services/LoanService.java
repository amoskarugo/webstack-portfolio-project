package com.restApi.bankPortal.services;

import com.restApi.bankPortal.domain.entities.Loan;

import java.util.Optional;

public interface LoanService {

    Optional<Loan> findById(Long id);
    Optional<Loan> findByAccountNumber(Long accountNumber);

}
