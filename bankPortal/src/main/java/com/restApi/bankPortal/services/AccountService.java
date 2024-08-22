package com.restApi.bankPortal.services;

import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;

public interface AccountService {
    ApiResponse<?> createAccount(AccountDto requestBody);
}
