package com.restApi.bankPortal.controllers;


import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {

  private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto requestBody){

        ApiResponse<?> response = accountService.createAccount(requestBody);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
