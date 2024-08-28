package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanRepo extends CrudRepository<Loan, Long> {

    Optional<Loan> findByAccountAccountNo(Long accountNumber);
}
