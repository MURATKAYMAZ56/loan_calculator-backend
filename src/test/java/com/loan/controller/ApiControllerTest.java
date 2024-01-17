package com.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.controller.request.LoanRequestDto;
import com.loan.controller.response.LoanResponseDto;
import com.loan.service.LoanCalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ApiController.class)
public class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanCalculateService loanCalculateService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        LoanResponseDto expectedResponseDto = buildLoanResponseDto();
        Mockito.when(loanCalculateService.calculateAndSave(Mockito.any())).thenReturn(expectedResponseDto);

        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(10);
        requestDto.setInterestRate(BigDecimal.valueOf(1.1));
        requestDto.setEmail("test@mail.com");

        MvcResult mvcResult = buildMvcPerform(requestDto)
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("{\"emi\":10}", actualResponseBody);
    }

    @Test
    void whenLoanAmountIsNull_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(null);

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanAmountIsNotPositive_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.valueOf(-100));

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanInstallmentIsLowerThanZero_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(-1);

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanInstallmentIsBiggerThanThirty_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(31);

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanInterestRateIsNull_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(null);

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanInterestRateIsLowerThanZero_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(BigDecimal.valueOf(-1));

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenLoanInterestRateIsBiggerThanHundred_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(BigDecimal.valueOf(1000));

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenEmailIsNull_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(BigDecimal.valueOf(30));
        requestDto.setEmail(null);

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenEmailIsEmpty_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(BigDecimal.valueOf(30));
        requestDto.setEmail("");

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void whenEmailIsNotValidFormat_thenReturns400() throws Exception {
        LoanRequestDto requestDto = new LoanRequestDto();
        requestDto.setAmount(BigDecimal.ONE);
        requestDto.setInstalment(1);
        requestDto.setInterestRate(BigDecimal.valueOf(30));
        requestDto.setEmail("test");

        buildMvcPerform(requestDto)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private ResultActions buildMvcPerform(LoanRequestDto requestDto) throws Exception {
        return mockMvc.perform(post("/api/loans")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(requestDto)));
    }

    private LoanResponseDto buildLoanResponseDto() {
        LoanResponseDto responseDto = new LoanResponseDto();
        responseDto.setEmi(BigDecimal.TEN);
        return responseDto;
    }
}
