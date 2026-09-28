package com.example.microsave.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record GroupDTO(Long id, @NotBlank @Size(max=100) String name, @Size(max=500) String description, LocalDate createdDate, String status) {}
