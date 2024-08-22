package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
