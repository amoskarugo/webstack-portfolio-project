package com.restApi.bankPortal.domain.entities;

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
    private String currency;
    @Column(nullable = false)
    private String account_type;

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
