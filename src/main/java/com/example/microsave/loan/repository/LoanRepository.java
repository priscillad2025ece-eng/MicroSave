package com.example.microsave.loan.repository;

import com.example.microsave.loan.entity.Loan;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByMember_Id(Long memberId);

    @Query("""
        SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END
        FROM Loan l
        WHERE l.member.id = :memberId
          AND l.status = :status
          AND l.outstandingAmount > :amount
    """)
    boolean existsByMember_IdAndStatusAndOutstandingAmountGreaterThan(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            @Param("amount") BigDecimal amount
    );

    @Query("""
        SELECT COALESCE(SUM(l.outstandingAmount), 0)
        FROM Loan l
    """)
    BigDecimal outstandingAll();

    @Query("""
        SELECT COALESCE(SUM(l.outstandingAmount), 0)
        FROM Loan l
        WHERE l.member.group.id = :id
    """)
    BigDecimal outstandingByGroup(
            @Param("id") Long id
    );

    @Query("""
        SELECT COALESCE(SUM(l.amount), 0)
        FROM Loan l
        WHERE l.member.id = :id
    """)
    BigDecimal totalAmountByMember(
            @Param("id") Long id
    );
}