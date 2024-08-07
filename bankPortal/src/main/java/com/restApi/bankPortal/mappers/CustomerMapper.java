package com.restApi.bankPortal.mappers;

import com.restApi.bankPortal.domain.dto.CustomerDto;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper implements Mapper<CustomerEntity, CustomerDto>{


    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerEntity toDto(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

    @Override
    public CustomerDto fromDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
}
