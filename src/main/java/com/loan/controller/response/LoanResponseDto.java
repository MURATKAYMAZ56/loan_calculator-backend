package com.loan.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanResponseDto {
    private BigDecimal emi;
}
