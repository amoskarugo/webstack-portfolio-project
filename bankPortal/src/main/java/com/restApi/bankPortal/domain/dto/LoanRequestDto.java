package com.restApi.bankPortal.domain.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restApi.bankPortal.enums.LoanType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequestDto {



    @NotNull(message = "amount cannot be empty!")
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotNull(message = "loan type cannot be empty!")
    @JsonProperty("loan_type")
    private LoanType loan_type;
    @NotNull(message = "account number cannot be empty!")
    @JsonProperty("account_number")
    private Long account_number;

}
