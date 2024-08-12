package com.restApi.bankPortal.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Configuration
@Table(name = "customers")
public class CustomerEntity {
    @Id
    private Long customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private LocalDate dob;
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private Timestamp created_at;
}
