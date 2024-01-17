package com.loan.repository;

import com.loan.model.LoanEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
