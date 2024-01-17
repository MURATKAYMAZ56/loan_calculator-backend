package com.loan.service;

import com.loan.controller.request.LoanRequestDto;
import com.loan.controller.response.LoanResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;

@ExtendWith(MockitoExtension.class)
public class LoanCalculateServiceTest {

    private static final BigDecimal LOAN_AMOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal LOAN_YEARLY_INTEREST_RATE = BigDecimal.valueOf(4.3);
    private static final int LOAN_INSTALLMENT = 2;
    private static final BigDecimal EXPECTED_EMI = BigDecimal.valueOf(782.37);
    @Mock
    private LoanService loanService;
    @InjectMocks
    private LoanCalculateService loanCalculateService;

    @Test
    public void testCalculateAndSave() {

        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(LOAN_AMOUNT);
        requestDto.setInstalment(LOAN_INSTALLMENT);
        requestDto.setInterestRate(LOAN_YEARLY_INTEREST_RATE);

        LoanResponseDto responseDto = loanCalculateService.calculateAndSave(requestDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(0, EXPECTED_EMI.compareTo(responseDto.getEmi()));
    }

    @Test
    public void testCalculateEmi() {
        BigDecimal monthlyInterestRate = LOAN_YEARLY_INTEREST_RATE.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);

        BigDecimal emi = loanCalculateService.calculateEmi(LOAN_AMOUNT, LOAN_INSTALLMENT, monthlyInterestRate);

        Assertions.assertEquals(0, EXPECTED_EMI.compareTo(emi));
    }

//    @Test
//    public void testCalculateEmiWhenExceptionalCase() {
//        BigDecimal monthlyInterestRate = LOAN_YEARLY_INTEREST_RATE.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);
//        Assertions.assertThrows(RuntimeException.class, () -> loanCalculateService.calculateEmi(LOAN_AMOUNT, LOAN_INSTALLMENT, monthlyInterestRate));
//    }
}
