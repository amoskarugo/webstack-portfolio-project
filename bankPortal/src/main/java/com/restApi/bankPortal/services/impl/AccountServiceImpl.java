package com.restApi.bankPortal.services.impl;

import com.restApi.bankPortal.Exceptions.InsufficientBalanceException;
import com.restApi.bankPortal.Exceptions.WrongPinException;
import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.domain.dto.DepositDto;
import com.restApi.bankPortal.domain.dto.TransferDto;
import com.restApi.bankPortal.domain.dto.Withdraw;
import com.restApi.bankPortal.domain.entities.Account;
import com.restApi.bankPortal.domain.entities.Branch;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.domain.entities.Transactions;
import com.restApi.bankPortal.enums.AccountStatus;
import com.restApi.bankPortal.enums.TransactionType;
import com.restApi.bankPortal.mappers.Mapper;
import com.restApi.bankPortal.repository.AccountRepository;
import com.restApi.bankPortal.repository.TransactionRepo;
import com.restApi.bankPortal.services.AccountService;
import com.restApi.bankPortal.utils.GenerateRandom;
import com.restApi.bankPortal.utils.GetLoggedInUser;
import com.restApi.bankPortal.utils.TransactionUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    HashMap<String, Object> data = new HashMap<>();
    final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    GenerateRandom generateRandom;


    private final TransactionUtil transactionUtil;

    @Autowired
    GetLoggedInUser loggedInUser;

    private final Mapper<Account, AccountDto> accountMapper;

    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    BranchServiceImpl branchService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepo transactionRepo;

    public AccountServiceImpl(Mapper<Account, AccountDto> accountMapper, TransactionUtil transactionUtil) {
        this.accountMapper = accountMapper;
        this.transactionUtil = transactionUtil;
    }

    @Override
    public ApiResponse<?> createAccount(AccountDto requestBody) {
        data.clear();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails && authentication.isAuthenticated())
            logger.info("auth instance of user detail service");
        UserDetails principal = (UserDetails) authentication.getPrincipal();


        String username = principal.getUsername();
        if (username == null) {

            data.put("error", "failed to get customer details, try again later!");
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "something went wrong!", data);

        }
//
        logger.info(username);
        CustomerEntity customer = customerService.findByEmail(username);


        Branch branch = branchService.findByBranchName(requestBody.getBranch_name());

        Long accountNumber = generateRandom.generateAccountNumber();


        requestBody.setCustomer_id(customer.getCustomer_id());
        requestBody.setAccountNo(accountNumber);
        requestBody.setAccount_status(AccountStatus.LOCKED);
        requestBody.setAccountType(requestBody.getAccountType());
        requestBody.setBranch(branch);
        requestBody.setCustomer(customer);


        Account newAccountDetails = accountMapper.toEntity(requestBody);

        Account createdAccount = accountRepository.save(newAccountDetails);
        data.put("account_no", createdAccount.getAccountNo());
        data.put("account_balance", createdAccount.getBalance());
        data.put("currency", createdAccount.getCurrency());

        return new ApiResponse<>(HttpStatus.CREATED.value(), "account created successfully!", data);
    }


    @Override
    public ApiResponse<?> deposit(DepositDto request) {
        data.clear();
        BigDecimal amount = request.amount();
        Long account_no = request.accountNumber();
        logger.info("account number: {}", account_no);

        HashMap<String, Object> data = new HashMap<>();
        Account depositToAccount = accountRepository.findByAccountNo(account_no)
                .orElseThrow(

                        ()-> new EntityNotFoundException("error while retrieving account " + account_no +  ", check account number and try again!")
                );


        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0){
            depositToAccount.setBalance(
                    depositToAccount.getBalance().add(amount)
            );
        }else {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        Account account = accountRepository.save(depositToAccount);

        Transactions transaction = transactionUtil.insertTransaction(TransactionType.DEPOSIT, depositToAccount.getAccountNo(),
                depositToAccount.getAccountNo(),
                depositToAccount.getBalance(),
                depositToAccount
                );
        Transactions transaction_info = transactionRepo.save(transaction);



        data.put("bal", account.getBalance());
        data.put("transaction_id", transaction_info.getTransaction_id());
        data.put("date", transaction_info.getIssued_at());

        return new ApiResponse<>(HttpStatus.CREATED.value(), "deposit successful!", data);
    }

    @Override
    public ApiResponse<?> withdraw(Withdraw request) {
        data.clear();
        Long account_no = request.accountNumber();
        BigDecimal amount = request.amount();

        Account withdrawFromAccount = accountRepository.findByAccountNo(account_no)
                .orElseThrow(

                        ()-> new EntityNotFoundException("Account not found with number: " +
                                account_no)
                );
        if (withdrawFromAccount.getPin() != request.pin())
            throw new WrongPinException("incorrect pin!");

        BigDecimal currentBal = withdrawFromAccount.getBalance();

        if (amount.compareTo(currentBal) > 0) {
            throw  new InsufficientBalanceException("insufficient balance to perform this transaction!");
        }

        withdrawFromAccount.setBalance(withdrawFromAccount.getBalance().subtract(amount));
        Account account = accountRepository.save(withdrawFromAccount);
        Transactions transaction = Transactions.builder()
                .transactionType(TransactionType.WITHDRAW)
                .from_account(account.getAccountNo())
                .account(account)
                .amount(amount)
                .to_account(account.getAccountNo())
                .build();

        Transactions insertedTransaction = transactionRepo.save(transaction);
        data.put("transactionID", insertedTransaction.getTransaction_id());
        data.put("amount", insertedTransaction.getAmount());
        data.put("date", insertedTransaction.getIssued_at());
        data.put("bal", account.getBalance());

        return new ApiResponse<>(HttpStatus.CREATED.value(), "withdraw success!", data);
    }

    @Override
    public ApiResponse<?> transfer(TransferDto request) {
        data.clear();

        Long toAccount = request.toAccount();
        Long fromAccount = request.fromAccount();
        int pin = request.pin();
        BigDecimal amount = request.amount();

        Account transferFromAccount = accountRepository.findByAccountNo(fromAccount)
                .orElseThrow(

                        ()-> new EntityNotFoundException("Account not found with number: " +
                                fromAccount)
                );
        Account transferToAccount = accountRepository.findByAccountNo(toAccount)
                .orElseThrow(

                        ()-> new EntityNotFoundException("Recipient with account number " + toAccount +" not found!"
                                )
                );

        if (transferFromAccount.getPin() != pin)
            throw new WrongPinException("Incorrect pin!");

        BigDecimal currentBal = transferFromAccount.getBalance();

        if (amount.compareTo(currentBal) > 0) {
            throw  new InsufficientBalanceException("insufficient balance to perform this transaction!");
        }
        transferFromAccount.setBalance(transferFromAccount.getBalance().subtract(amount));

        transferToAccount.setBalance(transferToAccount.getBalance().add(amount));

        Account sender = accountRepository.save(transferFromAccount);
        Account recipient = accountRepository.save(transferToAccount);

        Transactions senderTransactionInfo = transactionUtil.debitTransaction(
                sender.getAccountNo(), recipient.getAccountNo(), amount, sender
        );

        Transactions recipientTransactionInfo = transactionUtil.creditTransaction(
                sender.getAccountNo(), recipient.getAccountNo(), amount, recipient
        );


        Transactions savedSenderTransaction = transactionRepo.save(senderTransactionInfo);
        Transactions savedRecipientTransaction = transactionRepo.save(recipientTransactionInfo);


        data.put("transactionID", savedSenderTransaction.getTransaction_id());
        data.put("recipient", recipient.getAccountNo());
        data.put("amount", amount);
        data.put("balance", sender.getBalance());



        return new ApiResponse<>(HttpStatus.CREATED.value(), "transfer successful", data);
    }


}
