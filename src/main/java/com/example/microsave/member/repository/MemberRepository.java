package com.example.microsave.member.repository;
import com.example.microsave.member.entity.Member; import java.util.List; import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member,Long>{List<Member> findByGroup_Id(Long groupId);}
