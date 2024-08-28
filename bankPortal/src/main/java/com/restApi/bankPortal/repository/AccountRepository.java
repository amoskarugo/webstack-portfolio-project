package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountNo(Long account_no);
}
