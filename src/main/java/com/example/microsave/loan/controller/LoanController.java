package com.example.microsave.loan.controller;

import com.example.microsave.loan.dto.LoanDTO;
import com.example.microsave.loan.service.LoanService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return service.all();
    }

    @GetMapping("/{id}")
    public LoanDTO getLoanById(@PathVariable Long id) {
        return service.one(id);
    }

    @GetMapping("/member/{memberId}")
    public List<LoanDTO> getLoansByMember(
            @PathVariable Long memberId) {
        return service.byMember(memberId);
    }

    @GetMapping("/total-outstanding")
    public BigDecimal getTotalOutstanding() {
        return service.totalOutstanding();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDTO createLoan(
            @Valid @RequestBody LoanDTO data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public LoanDTO updateLoan(
            @PathVariable Long id,
            @Valid @RequestBody LoanDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable Long id) {
        service.delete(id);
    }
}

