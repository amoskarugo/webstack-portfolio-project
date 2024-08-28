package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.Exceptions.InsufficientBalanceException;
import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.LoanRequestDto;
import com.restApi.bankPortal.domain.dto.PayLoanDto;
import com.restApi.bankPortal.domain.entities.Account;
import com.restApi.bankPortal.domain.entities.Loan;
import com.restApi.bankPortal.domain.entities.Transactions;
import com.restApi.bankPortal.enums.LoanStatus;
import com.restApi.bankPortal.enums.TransactionType;
import com.restApi.bankPortal.mappers.LoanMapper;
import com.restApi.bankPortal.mappers.Mapper;
import com.restApi.bankPortal.repository.AccountRepository;
import com.restApi.bankPortal.repository.LoanRepo;
import com.restApi.bankPortal.repository.TransactionRepo;
import com.restApi.bankPortal.services.LoanService;
import com.restApi.bankPortal.utils.TransactionUtil;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;


@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    TransactionRepo transactionRepo;
    private final TransactionUtil transactionUtil;
    HashMap<String, Object> data = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    private final LoanRepo loanRepo;
    private final Mapper<Loan, LoanRequestDto> loanMapper;
    private final AccountRepository accountRepository;

    public LoanServiceImpl(TransactionUtil transactionUtil, LoanRepo loanRepo, Mapper<Loan, LoanRequestDto> loanMapper, AccountRepository accountRepository) {
        this.transactionUtil = transactionUtil;
        this.loanRepo = loanRepo;
        this.loanMapper = loanMapper;
        this.accountRepository = accountRepository;
    }


    @Override
    public Optional<Loan> findById(Long id) {
        return loanRepo.findById(id);
    }

    @Override
    public Optional<Loan> findByAccountNumber(Long accountNumber) {
        return loanRepo.findByAccountAccountNo(accountNumber);
    }

    public ApiResponse<?> createLoan(LoanRequestDto request) {




        Long account_no = request.getAccount_number();
        BigDecimal amount = request.getAmount();
        logger.info("account no " + account_no);

        Account customerAccount = accountRepository.findByAccountNo(account_no).orElseThrow(
                ()-> new EntityNotFoundException("error while retrieving account " +
                        account_no +  ", check account number and try again!")
        );

        Loan loan = loanMapper.toEntity(request);
        loan.setAccount(customerAccount);
        loan.setLoan_status(LoanStatus.ACTIVE);

        Loan borrowedLoan = loanRepo.save(loan);
        customerAccount.setBalance(customerAccount.getBalance().add(amount));

        accountRepository.save(customerAccount);


        Transactions transaction = transactionUtil.insertTransaction(TransactionType.LOAN_CREDIT, null,
                customerAccount.getAccountNo(),amount, customerAccount);


        transactionRepo.save(transaction);
        data.put("load_id", borrowedLoan.getLoan_id());
        data.put("amount", borrowedLoan.getAmount());
        data.put("payment_start_date", borrowedLoan.getRepaymentDateStart());
        data.put("due_date", borrowedLoan.getDue_date());
        data.put("date_issued", borrowedLoan.getIssued_at());

        String msg = "loan successfully  credited to account " + account_no;

        return new ApiResponse<>(HttpStatus.OK.value(), msg, data);
    }

    public ApiResponse<?> payLoan(PayLoanDto request) {
        data.clear();
        Long loan_id = request.getLoan_id();
        BigDecimal amount = request.getAmount();
        Long account_number = request.getAccount_number();

        Loan loanToPay = loanRepo.findById(loan_id).orElseThrow(
                ()-> new EntityNotFoundException("No pending loans!")
        );

        Account deductFromAccount = accountRepository.findByAccountNo(account_number).orElseThrow(
                ()-> new EntityNotFoundException("cannot retrieve the account, try again!")
        );

        if (deductFromAccount.getBalance().compareTo(amount) < 0)
            throw new InsufficientBalanceException("insufficient balance in your account to pay this loan!");

        if(amount.compareTo(loanToPay.getAmount()) > 0) {
            deductFromAccount.setBalance(deductFromAccount.getBalance().subtract(amount));
            loanToPay.setAmount(BigDecimal.ZERO);
            loanToPay.setLoan_status(LoanStatus.FULLY_PAID);
        }

        loanToPay.setAmount(loanToPay.getAmount().subtract(amount));

        accountRepository.save(deductFromAccount);
        Loan remainingToPay = loanRepo.save(loanToPay);

        Transactions transaction = transactionUtil.insertTransaction(TransactionType.LOAN_REPAYMENT, deductFromAccount.getAccountNo(),
                null,amount,  deductFromAccount);
        Transactions createdTransaction = transactionRepo.save(transaction);

        data.put("transaction_id", transaction.getTransaction_id());
        data.put("loan_bal", remainingToPay.getAmount());
        data.put("amount paid", amount);
        return new ApiResponse<>(HttpStatus.OK.value(), "loan paid successfully!", data);
    }
}
