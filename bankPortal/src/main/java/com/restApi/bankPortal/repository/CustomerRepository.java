package com.restApi.bankPortal.repository;


import com.restApi.bankPortal.domain.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);

    Optional<CustomerEntity> findByEmail(String email);
}
