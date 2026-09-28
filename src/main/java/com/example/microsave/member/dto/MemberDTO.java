package com.example.microsave.member.dto;
import jakarta.validation.constraints.*; import java.time.LocalDate;
public record MemberDTO(Long id,@NotBlank @Size(max=100) String name,@Size(max=20) String phone,@Size(max=300) String address,LocalDate joinDate,String status,@NotNull @Positive Long groupId){}
