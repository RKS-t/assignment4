package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
