package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.domain.entities.Account;
import com.restApi.bankPortal.domain.entities.Branch;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.mappers.Mapper;
import com.restApi.bankPortal.repository.AccountRepository;
import com.restApi.bankPortal.services.AccountService;
import com.restApi.bankPortal.utils.GenerateRandom;
import com.restApi.bankPortal.utils.GetLoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    GenerateRandom generateRandom;

    @Autowired
    GetLoggedInUser loggedInUser;

    private final Mapper<Account, AccountDto> accountMapper;
    CustomerServiceImpl customerService;
    BranchServiceImpl branchService;
    AccountRepository accountRepository;

    public AccountServiceImpl(Mapper<Account, AccountDto> accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ApiResponse<?> createAccount(AccountDto requestBody) {
        
        Long customer_id, branch_id;

        HashMap<String, Object> data = new HashMap<>();

        UserDetails user = loggedInUser.getLoggedInUserDetails();
        if (user == null) {
            data.put("message", "failed, try again later!");
            return new ApiResponse<>("something went wrong!", false, data);
        }
        Optional<CustomerEntity> customer = customerService.findByEmail(user.getUsername());
        customer.orElseThrow();
        Branch branch = branchService.findByBranchName(requestBody.getBranch_name());

        
        Long accountNumber = generateRandom.generateRandomNumber();

        requestBody.setCustomer_id(customer.get().getCustomer_id());
        requestBody.setAccount_no(accountNumber);
        requestBody.setBranch_id(branch.getBranch_id());

        Account newAccountDetails = accountMapper.toEntity(requestBody);

        Account createdAccount = accountRepository.save(newAccountDetails);

        data.put("account_no", createdAccount.getAccount_no());
        data.put("branch_code", createdAccount.getBranch_id());
        data.put("account_balance", createdAccount.getBalance());
        data.put("currency", createdAccount.getCurrency());

        return new ApiResponse<>("account created successfully!", true, data);
    }
}
