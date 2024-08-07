package com.restApi.bankPortal.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Configuration
@Table(name = "customers")
public class CustomerEntity {

    private long customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Date dob;
}
