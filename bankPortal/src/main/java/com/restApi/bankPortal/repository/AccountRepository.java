package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountNo(Long account_no);
}
