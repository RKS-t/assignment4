package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {

    List<Plan> findPlanByMemberUsername(String username);

    default List<Plan> findPlanByMemberUsernameOrElseThrow(String username){

        List<Plan> plans = findPlanByMemberUsername(username);
        if (plans.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Does not exist plan by user =" + username
            );
        }
        return plans;
    }

    List<Plan> findPlanByTargetDate(LocalDate date);

    default List<Plan> findPlanByTargetDateOrElseThrow(LocalDate date){

        List<Plan> plans = findPlanByTargetDate(date);
        if (plans.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Does not exist plan on date =" + date
            );
        }
        return plans;
    }

    default Plan findPlanByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dose not exist plan by id =" + id
                )
        );
    }


}
