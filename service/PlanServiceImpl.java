package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.plan.PlanRequestDto;
import com.example.scheduleproject.dto.plan.PlanResponseDto;
import com.example.scheduleproject.dto.plan.SinglePlanResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.repository.MemberRepository;
import com.example.scheduleproject.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;

    @Override
    public SinglePlanResponseDto save(PlanRequestDto dto, MemberResponseDto loginUser) {

        Member findMember = memberRepository.findMemberByEmailOrElseThrow(loginUser.getEmail());

        Plan plan = new Plan(dto.getTitle(), dto.getContents());
        plan.setMember(findMember);

        planRepository.save(plan);

        return new SinglePlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getContents(),
                plan.getCreatedAt(),
                plan.getModifiedAt(),
                loginUser.getEmail(),
                loginUser.getUsername()
        );
    }

    @Override
    public List<PlanResponseDto> findAll() {
        return planRepository.findAll().stream().map(PlanResponseDto::toDto).toList();
    }


}
