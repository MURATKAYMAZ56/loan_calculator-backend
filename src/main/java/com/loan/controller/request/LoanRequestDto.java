package com.loan.controller.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequestDto {
    @NotNull
    @Positive
    private BigDecimal amount;
    @Min(1)
    @Max(30)
    private int instalment;
    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal interestRate;
    @NotNull
    @NotEmpty
    @Email
    private String email;
}
