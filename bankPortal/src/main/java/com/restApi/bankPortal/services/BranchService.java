package com.restApi.bankPortal.services;

import com.restApi.bankPortal.domain.entities.Branch;

import java.util.Optional;

public interface BranchService {

    Branch findByBranchName(String name);
}
