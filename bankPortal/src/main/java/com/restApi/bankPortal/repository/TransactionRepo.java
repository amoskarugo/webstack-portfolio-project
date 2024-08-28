package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepo extends CrudRepository<Transactions, Long> {


    @Query(value = "SELECT * FROM transactions WHERE account_no = :account_no", nativeQuery = true)
    List<Transactions> getAllTransactions(@Param("account_no") Long account_no);

}
