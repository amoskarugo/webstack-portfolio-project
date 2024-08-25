package com.restApi.bankPortal.apiResponseHandler;

import com.restApi.bankPortal.domain.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {

    private int httpStatusCode;
    private String message;
    private T data;

}
