package com.loan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private int instalment;
    @Column(nullable = false)
    private BigDecimal interestRate;
    @Column(nullable = false)
    private BigDecimal emi;
    @Column(nullable = false)
    private String email;
}
