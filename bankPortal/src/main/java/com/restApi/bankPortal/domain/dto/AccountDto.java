package com.restApi.bankPortal.domain.dto;

import com.restApi.bankPortal.domain.entities.Branch;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long accountNo;
    private Long customer_id;
    private BigDecimal balance;
    private AccountStatus account_status;
    private String currency;
    private String accountType;
    private String branch_name;
    private Branch branch;
    private int pin;
    private CustomerEntity customer;
}
