package com.loan.controller;

import com.loan.controller.request.LoanRequestDto;
import com.loan.controller.response.LoanResponseDto;
import com.loan.service.LoanCalculateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/loans")
public class ApiController {

    private final LoanCalculateService loanCalculateService;

    @PostMapping
    public ResponseEntity<LoanResponseDto> create(@Valid @RequestBody LoanRequestDto payload){
        return ResponseEntity.ok(loanCalculateService.calculateAndSave(payload));
    }
}
