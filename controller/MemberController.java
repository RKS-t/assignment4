package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.SignUpResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@Valid @RequestBody SignUpRequestDto dto){

        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getPasswordCheck(),
                        dto.getUsername()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

}
