package com.restApi.bankPortal.repository;

import com.restApi.bankPortal.domain.entities.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {

    Branch findByName(String branch_name);
}
