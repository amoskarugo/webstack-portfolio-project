package com.restApi.bankPortal.domain.entities;


import com.restApi.bankPortal.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime issued_at= LocalDateTime.now();

    private Long from_account;
    private Long to_account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no", updatable = false)
    private Account account;
}
