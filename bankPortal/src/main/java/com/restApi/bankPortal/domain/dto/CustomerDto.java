package com.restApi.bankPortal.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private long customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Date dob;
}
