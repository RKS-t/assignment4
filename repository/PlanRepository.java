package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {

    List<Plan> findPlanByMemberUsername(String username);

    default List<Plan> findPlanByMemberUsernameOrElseThrow(String username){

        List<Plan> plans = findPlanByMemberUsername(username);
        if (plans.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist plan");
        }
        return plans;
    }

}
