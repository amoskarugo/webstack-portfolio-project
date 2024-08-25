package com.restApi.bankPortal.domain.dto;

import java.math.BigDecimal;

public record Withdraw(Long accountNumber, BigDecimal amount, int pin) {
}
