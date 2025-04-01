package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.SignUpResponseDto;

public interface MemberService {

    SignUpResponseDto signUp(String email, String password, String passwordCheck, String username);

}
