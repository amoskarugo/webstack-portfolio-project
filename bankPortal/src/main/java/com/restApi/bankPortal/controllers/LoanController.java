package com.restApi.bankPortal.controllers;


import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.LoanRequestDto;
import com.restApi.bankPortal.domain.dto.PayLoanDto;
import com.restApi.bankPortal.services.impl.LoanServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/loan/")
@RestController
public class LoanController {


    private final LoanServiceImpl loanService;

    public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowLoan(@Valid @RequestBody LoanRequestDto request){

        ApiResponse<?> response = loanService.createLoan(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pay_loan")
    public ResponseEntity<?> payLoan(@Valid @RequestBody PayLoanDto request){

        ApiResponse<?> response = loanService.payLoan(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
