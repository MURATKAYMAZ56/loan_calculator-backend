package com.loan.service;

import com.loan.model.LoanEntity;
import com.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanEntity saveLoan(LoanEntity entity) {
        return loanRepository.save(entity);
    }
}
