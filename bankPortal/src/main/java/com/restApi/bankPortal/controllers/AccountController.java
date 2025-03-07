package com.restApi.bankPortal.controllers;


import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.domain.dto.DepositDto;
import com.restApi.bankPortal.domain.dto.TransferDto;
import com.restApi.bankPortal.domain.dto.Withdraw;
import com.restApi.bankPortal.services.AccountService;
import com.restApi.bankPortal.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

  private final AccountService accountService;
  private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto requestBody){

        ApiResponse<?> response = accountService.createAccount(requestBody);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositToAccount(@RequestBody DepositDto request){

        ApiResponse<?> response = accountService.deposit(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@RequestBody Withdraw request){

        ApiResponse<?> response = accountService.withdraw(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferDto request){

        ApiResponse<?> response = accountService.transfer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transaction/history/{account_no}")
    public ResponseEntity<?> history(@PathVariable("account_no") Long account_no)
    {

        ApiResponse<?> response = transactionService.transactionHistory(account_no);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
