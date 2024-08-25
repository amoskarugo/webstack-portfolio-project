package com.restApi.bankPortal.services;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.domain.dto.DepositDto;
import com.restApi.bankPortal.domain.dto.TransferDto;
import com.restApi.bankPortal.domain.dto.Withdraw;

public interface AccountService {
    ApiResponse<?> createAccount(AccountDto requestBody);
    ApiResponse<?> deposit(DepositDto requestBody);

    ApiResponse<?> withdraw(Withdraw withdrawDto);

    ApiResponse<?> transfer(TransferDto request);
}
