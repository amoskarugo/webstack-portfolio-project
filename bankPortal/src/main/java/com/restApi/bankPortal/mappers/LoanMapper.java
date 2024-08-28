package com.restApi.bankPortal.mappers;

import com.restApi.bankPortal.domain.dto.LoanRequestDto;
import com.restApi.bankPortal.domain.entities.Loan;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper implements Mapper<Loan, LoanRequestDto>{

    private final ModelMapper modelMapper;

    public LoanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LoanRequestDto toDto(Loan loan) {
        return modelMapper.map(loan, LoanRequestDto.class);
    }

    @Override
    public Loan toEntity(LoanRequestDto borrowLoanDto) {
        return modelMapper.map(borrowLoanDto, Loan.class);
    }
}
