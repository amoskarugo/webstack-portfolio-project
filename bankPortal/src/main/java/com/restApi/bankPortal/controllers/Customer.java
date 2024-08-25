package com.restApi.bankPortal.controllers;


import com.restApi.bankPortal.apiResponseHandler.ApiResponse;
import com.restApi.bankPortal.domain.dto.LoginForm;
import com.restApi.bankPortal.domain.dto.CustomerDto;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import com.restApi.bankPortal.mappers.Mapper;
import com.restApi.bankPortal.services.impl.CustomerServiceImpl;
import com.restApi.bankPortal.utils.GenerateRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/")
public class Customer {

    @Autowired
    GenerateRandom generateUUID;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Mapper<CustomerEntity, CustomerDto> customerMapper;
    private  final CustomerServiceImpl customerService;

    public Customer(Mapper<CustomerEntity, CustomerDto> customerMapper, CustomerServiceImpl customerService) {
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
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CONFLICT.value(),
                    "email already exists!", null));

        customerDto.setCustomer_id(customer_id);
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        CustomerEntity savedCustomerEntity = customerService.createCustomer(customerEntity);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(),"customer created successfully!",
                customerMapper.toDto(savedCustomerEntity)), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm requestBody){

        if(!customerService.existByEmail(requestBody.username()))
            return ResponseEntity.ok("Email does not exist");

        ApiResponse<?> response = customerService.authenticate(requestBody);
        return ResponseEntity.ok(response);
    }

}
