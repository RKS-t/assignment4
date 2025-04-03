package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.exception.NullResponseException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {

    List<Plan> findPlanByMemberUsername(String username);

    List<Plan> findPlanByTargetDate(LocalDate date);

    default Plan findPlanByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new NullResponseException("Dose not exist plan by id =" + id));
    }


}
