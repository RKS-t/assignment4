package com.example.scheduleproject.controller;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.plan.PlanRequestDto;
import com.example.scheduleproject.dto.plan.PlanResponseDto;
import com.example.scheduleproject.dto.plan.SinglePlanResponseDto;
import com.example.scheduleproject.service.PlanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<SinglePlanResponseDto> save (@RequestBody PlanRequestDto dto, HttpSession session){

        MemberResponseDto loginUser = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        SinglePlanResponseDto planResponseDto = planService.save(dto, loginUser);

        return new ResponseEntity<>(planResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {

        List<PlanResponseDto> planResponseDtoList = planService.findAll();

        return new ResponseEntity<>(planResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<PlanResponseDto>> findPlanByUserName(@PathVariable String username){

        List<PlanResponseDto> planResponseDtoList = planService.findPlanByUserName(username);

        return new ResponseEntity<>(planResponseDtoList, HttpStatus.OK);
    }



}
