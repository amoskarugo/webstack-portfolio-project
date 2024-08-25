package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.domain.entities.Branch;
import com.restApi.bankPortal.repository.BranchRepository;
import com.restApi.bankPortal.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;
    @Override
    public Branch findByBranchName(String name) {
        return branchRepository.findByName(name);
    }
}
