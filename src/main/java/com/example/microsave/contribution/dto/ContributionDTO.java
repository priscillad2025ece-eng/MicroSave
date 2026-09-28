package com.example.microsave.contribution.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.LocalDate;
public record ContributionDTO(Long id,@NotNull @Positive Long memberId,@NotNull @DecimalMin(value="0.01") BigDecimal amount,@NotNull LocalDate contributionDate,@NotNull @Min(1) @Max(53) Integer weekNumber,@Size(max=300) String remarks){}
