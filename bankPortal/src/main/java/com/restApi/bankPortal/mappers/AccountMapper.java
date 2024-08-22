package com.restApi.bankPortal.mappers;

import com.restApi.bankPortal.domain.dto.AccountDto;
import com.restApi.bankPortal.domain.entities.Account;
import com.restApi.bankPortal.domain.entities.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper implements Mapper<Account, AccountDto>{

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDto toDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }
}
