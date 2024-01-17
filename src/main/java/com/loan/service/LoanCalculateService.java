package com.loan.service;

import com.loan.controller.request.LoanRequestDto;
import com.loan.controller.response.LoanResponseDto;
import com.loan.model.LoanEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL128;

@RequiredArgsConstructor
@Service
public class LoanCalculateService {
    private final LoanService loanService;

    public LoanResponseDto calculateAndSave(LoanRequestDto requestDto) {

        BigDecimal amount = requestDto.getAmount();
        int installment = requestDto.getInstalment();
        BigDecimal interestRate = requestDto.getInterestRate().divide(BigDecimal.valueOf(12), DECIMAL128);

        BigDecimal emi = calculateEmi(amount, installment, interestRate);

        LoanEntity model = new LoanEntity();
        model.setAmount(amount);
        model.setInstalment(installment);
        model.setInterestRate(interestRate);
        model.setEmi(emi);
        model.setEmail(requestDto.getEmail());

        loanService.saveLoan(model);

        LoanResponseDto responseDto = new LoanResponseDto();
        responseDto.setEmi(emi);
        return responseDto;
    }

    public BigDecimal calculateEmi(BigDecimal amount, int installment, BigDecimal monthlyInterestRate){
        BigDecimal x = monthlyInterestRate.add(BigDecimal.ONE).pow(installment);
        BigDecimal p1 = monthlyInterestRate.multiply(amount).multiply(x);
        BigDecimal p2 = x.subtract(BigDecimal.ONE);
        return p1.divide(p2, DECIMAL128).setScale(2, BigDecimal.ROUND_UP);
    }
}
