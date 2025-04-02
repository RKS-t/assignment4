package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.plan.PlanRequestDto;
import com.example.scheduleproject.dto.plan.PlanResponseDto;
import com.example.scheduleproject.dto.plan.SinglePlanResponseDto;

import java.util.List;

public interface PlanService {

    SinglePlanResponseDto save(PlanRequestDto dto, MemberResponseDto loginUser);
    List<PlanResponseDto> findAll();
    List<PlanResponseDto> findPlanByUserName(String username);
}
