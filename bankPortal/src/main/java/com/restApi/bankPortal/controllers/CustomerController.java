package com.restApi.bankPortal.controllers;


import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.CustomerDto;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.mappers.Mapper;
import com.restApi.bankPortal.services.impl.CustomerServiceImpl;
import com.restApi.bankPortal.utils.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    GenerateUUID generateUUID;

    private final Mapper<CustomerEntity, CustomerDto> customerMapper;
    private  final CustomerServiceImpl customerService;

    public CustomerController(Mapper<CustomerEntity, CustomerDto> customerMapper, CustomerServiceImpl customerService) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @PutMapping("/register")
    public ResponseEntity<ApiResponse<CustomerDto>> registerCustomer(@RequestBody CustomerDto customerDto){

        Long customer_id = generateUUID.generateRandomNumber();
        //Ensure generated customer id does not exist
        while (customerService.findOne(customer_id).isPresent())
            customer_id = generateUUID.generateRandomNumber();

        if (customerService.existByEmail(customerDto.getEmail()))
            return ResponseEntity.ok(new ApiResponse<>("email already exists!",
                    false, null));

        customerDto.setCustomer_id(customer_id);
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        CustomerEntity savedCustomerEntity = customerService.createCustomer(customerEntity);

        return ResponseEntity.ok(new ApiResponse<>("customer created successfully!",
                true, customerMapper.toDto(savedCustomerEntity)));
    }
}
