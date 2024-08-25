package com.restApi.bankPortal.domain.dto;

import java.math.BigDecimal;

public record DepositDto(Long accountNumber, BigDecimal amount) {
}
