package com.restApi.bankPortal.domain.entities;

import com.restApi.bankPortal.enums.AccountType;
import com.restApi.bankPortal.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Long account_no;
    @Column(name = "customer_id", nullable = false)
    private Long customer_id;
    private BigDecimal balance;
    private LocalDateTime created_at;
    @Column(nullable = false)
    private String account_status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "branch_id", nullable = false)
    private Long branch_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;


    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.balance = BigDecimal.valueOf(0.00);
    }

}
