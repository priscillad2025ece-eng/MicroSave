package com.example.microsave.loan.dto;
import jakarta.validation.constraints.*;import java.math.BigDecimal;import java.time.LocalDate;
public record LoanDTO(Long id,@NotNull @Positive Long memberId,@NotNull @DecimalMin(value="0.01") BigDecimal amount,@NotNull LocalDate loanDate,BigDecimal outstandingAmount,String status,@Size(max=300) String purpose,BigDecimal totalRepaid){}
