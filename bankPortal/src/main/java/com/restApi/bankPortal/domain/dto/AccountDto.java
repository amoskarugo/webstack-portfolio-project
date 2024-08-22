package com.restApi.bankPortal.domain.dto;

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
    private Long account_no;
    private Long customer_id;
    private BigDecimal balance;
    private String account_status;
    private String currency;
    private String account_type;
    private Long branch_id;
    private String branch_name;
}
