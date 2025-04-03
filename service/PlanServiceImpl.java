package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.plan.PlanRequestDto;
import com.example.scheduleproject.dto.plan.PlanResponseDto;
import com.example.scheduleproject.dto.plan.SinglePlanResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.repository.MemberRepository;
import com.example.scheduleproject.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;

    @Override
    public SinglePlanResponseDto savePlan(PlanRequestDto dto, MemberResponseDto loginUser) {

        Member findMember = memberRepository.findMemberByIdOrElseThrow(loginUser.getId());

        Plan plan = new Plan(dto.getTitle(), dto.getTargetDate(), dto.getContents());
        plan.setMember(findMember);

        planRepository.save(plan);

        return new SinglePlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getTargetDate(),
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

    @Override
    public List<PlanResponseDto> findPlanByUserName(String username) {
        return planRepository.findPlanByMemberUsername(username).stream().map(PlanResponseDto::toDto).toList();
    }

    @Override
    public List<PlanResponseDto> findPlanByDate(LocalDate date) {
        return planRepository.findPlanByTargetDate(date).stream().map(PlanResponseDto::toDto).toList();
    }

    @Override
    public SinglePlanResponseDto findPlanById(Long id) {

        Plan plan = planRepository.findPlanByIdOrElseThrow(id);

        return new SinglePlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getTargetDate(),
                plan.getContents(),
                plan.getCreatedAt(),
                plan.getModifiedAt(),
                plan.getMember().getEmail(),
                plan.getMember().getUsername()
        );
    }

    @Transactional
    @Override
    public SinglePlanResponseDto updatePlan(Long id, PlanRequestDto dto, MemberResponseDto loginUser) {

        Plan plan = planRepository.findPlanByIdOrElseThrow(id);

        if(plan.getMember().getId()!=loginUser.getId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"작성자가 아닙니다.");
        }

        plan.updatePlan(dto.getTitle(), dto.getTargetDate(), dto.getContents());

        return new SinglePlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getTargetDate(),
                plan.getContents(),
                plan.getCreatedAt(),
                plan.getModifiedAt(),
                plan.getMember().getEmail(),
                plan.getMember().getUsername()
        );
    }

    @Override
    public void deletePlan(Long id, MemberResponseDto loginUser) {

        Plan plan = planRepository.findPlanByIdOrElseThrow(id);

        if(plan.getMember().getId()!=loginUser.getId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"작성자가 아닙니다.");
        }

        planRepository.delete(plan);
    }


}
