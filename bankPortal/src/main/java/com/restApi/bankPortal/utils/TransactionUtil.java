package com.restApi.bankPortal.utils;

import com.restApi.bankPortal.domain.entities.Account;
import com.restApi.bankPortal.domain.entities.Transactions;
import com.restApi.bankPortal.enums.TransactionType;
import lombok.Builder;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class TransactionUtil {



    @Builder
    public Transactions insertTransaction(TransactionType type, Long from_account,
                                          Long to_account, BigDecimal amount,
                                          Account account){
        return Transactions.builder()
                .transactionType(type)
                .to_account(to_account)
                .from_account(from_account)
                .amount(amount)
                .account(account)
                .build();
    }

    @Builder
    public Transactions debitTransaction(Long sourceAccount, Long destinationAccount,
                                         BigDecimal amount, Account debitAccount){

        return Transactions.builder()
                .transactionType(TransactionType.TRANSFER)
                .from_account(sourceAccount)
                .to_account(destinationAccount)
                .amount(amount.negate())
                .account(debitAccount)
                .build();

    }

    @Builder
    public Transactions creditTransaction(Long sourceAccount, Long destinationAccount,
                                         BigDecimal amount, Account creditAccount){

        return Transactions.builder()
                .transactionType(TransactionType.TRANSFER)
                .from_account(sourceAccount)
                .to_account(destinationAccount)
                .amount(amount)
                .account(creditAccount)
                .build();

    }
}
