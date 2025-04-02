package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.plan.PlanRequestDto;
import com.example.scheduleproject.dto.plan.PlanResponseDto;

public interface PlanService {

    PlanResponseDto save(PlanRequestDto dto, MemberResponseDto loginUser);

}
