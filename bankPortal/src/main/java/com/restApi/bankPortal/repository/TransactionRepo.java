package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepo extends CrudRepository<Transactions, Long> {


}
