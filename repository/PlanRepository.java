package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.exception.NullResponseException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {

    List<Plan> findPlanByMemberUsername(String username);

    default List<Plan> findPlanByMemberUsernameOrElseThrow(String username){

        List<Plan> plans = findPlanByMemberUsername(username);
        if (plans.isEmpty()){
            throw new NullResponseException("Does not exist plan by user =" + username);
        }
        return plans;
    }

    List<Plan> findPlanByTargetDate(LocalDate date);

    default List<Plan> findPlanByTargetDateOrElseThrow(LocalDate date){

        List<Plan> plans = findPlanByTargetDate(date);
        if (plans.isEmpty()){
            throw new NullResponseException("Does not exist plan on date =" + date);
        }
        return plans;
    }

    default Plan findPlanByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new NullResponseException("Dose not exist plan by id =" + id));
    }


}
