package com.restApi.bankPortal.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restApi.bankPortal.customSerializers.CustomerSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize(using = CustomerSerializer.class)
public class CustomerDto {

    private Long customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private LocalDate dob;
    private Timestamp created_at;
    private String role;
}
