package com.restApi.bankPortal.domain.entities;


import com.restApi.bankPortal.enums.LoanStatus;
import com.restApi.bankPortal.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "loans")
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime issued_at = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDate due_date = LocalDate.now().plusMonths(13);

    @Builder.Default
    @Column(nullable = false)
    private LocalDate repaymentDateStart = LocalDate.now().plusDays(30);

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanType loan_type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStatus loan_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no", updatable = false)
    private Account account;

}
