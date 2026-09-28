package com.example.microsave.repayment.repository;
import com.example.microsave.repayment.entity.Repayment;import java.math.BigDecimal;import java.util.List;import org.springframework.data.jpa.repository.*;import org.springframework.data.repository.query.Param;
public interface RepaymentRepository extends JpaRepository<Repayment,Long>{List<Repayment>findByLoan_Id(Long loanId);@Query("select coalesce(sum(r.amount),0) from Repayment r where r.loan.member.id=:id")BigDecimal totalRepaidByMember(@Param("id")Long memberId);}
