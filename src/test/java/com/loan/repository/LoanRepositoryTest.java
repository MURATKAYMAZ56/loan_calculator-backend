package com.loan.repository;

import com.loan.model.LoanEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void testSave() {
        LoanEntity entity = new LoanEntity();
        entity.setEmail("email@mail.com");
        entity.setAmount(BigDecimal.ONE);
        entity.setInstalment(1);
        entity.setInterestRate(BigDecimal.ONE);
        entity.setEmi(BigDecimal.ONE);

        LoanEntity saved = loanRepository.save(entity);

        assertNotNull(saved);
        assertEquals(1, saved.getId());
    }
}
