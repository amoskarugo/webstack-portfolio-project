package com.restApi.bankPortal.domain.entities;

import com.restApi.bankPortal.enums.AccountStatus;
import com.restApi.bankPortal.enums.AccountType;
import com.restApi.bankPortal.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Long accountNo;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal balance;
    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime created_at= LocalDateTime.now();


    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private AccountStatus account_status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private int pin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", updatable = false)
    private Branch branch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", updatable = false)
    private CustomerEntity customer;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactions;


    @PrePersist
    public void onCreate(){
        this.balance = BigDecimal.ZERO;
    }

}
