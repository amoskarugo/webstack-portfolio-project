package com.restApi.bankPortal.apiResponseHandler;

import com.restApi.bankPortal.domain.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {

    private String message;
    private boolean success;
    private T data;

}
