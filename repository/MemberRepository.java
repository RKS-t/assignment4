package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {




}
