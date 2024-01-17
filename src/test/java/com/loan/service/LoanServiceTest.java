package com.loan.service;

import com.loan.model.LoanEntity;
import com.loan.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void testSaveLoan(){
        Mockito.when(loanRepository.save(Mockito.any())).thenReturn(buildLoanEntity());

        LoanEntity result = loanService.saveLoan(new LoanEntity());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("email@mail.com", result.getEmail());
    }

    private LoanEntity buildLoanEntity(){
        LoanEntity entity = new LoanEntity();
        entity.setEmail("email@mail.com");
        entity.setAmount(BigDecimal.ONE);
        entity.setInstalment(1);
        entity.setInterestRate(BigDecimal.ONE);
        entity.setEmi(BigDecimal.ONE);
        return entity;
    }
}
