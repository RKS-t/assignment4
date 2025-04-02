package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;


public interface MemberService {

    MemberResponseDto signUp(String email, String password, String passwordCheck, String username);
    MemberResponseDto findById(Long id);
    LoginResponseDto login(String email, String password);
}
