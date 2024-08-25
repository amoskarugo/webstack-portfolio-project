package com.restApi.bankPortal.domain.dto;

import java.math.BigDecimal;

public record TransferDto (Long fromAccount, Long toAccount, int pin, BigDecimal amount){
}
