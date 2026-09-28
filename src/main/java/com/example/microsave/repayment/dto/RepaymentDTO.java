package com.example.microsave.repayment.dto;
import jakarta.validation.constraints.*;import java.math.BigDecimal;import java.time.LocalDate;
public record RepaymentDTO(Long id,@NotNull@Positive Long loanId,@NotNull@DecimalMin(value="0.01")BigDecimal amount,@NotNull LocalDate repaymentDate,@Size(max=40)String paymentMethod,@Size(max=300)String remarks){}
