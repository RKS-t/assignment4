package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.UpdateMemberRequestDto;


public interface MemberService {

    MemberResponseDto signUp(SignUpRequestDto dto);
    MemberResponseDto findMemberById(Long id);
    LoginResponseDto login(String email, String password);
    void updateMember(Long id, UpdateMemberRequestDto dto);
    void deleteMember(Long id, String password);
}
