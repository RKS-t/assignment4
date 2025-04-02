package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;


public interface MemberService {

    MemberResponseDto signUp(String email, String password, String passwordCheck, String username);
    MemberResponseDto findMemberById(Long id);
    LoginResponseDto login(String email, String password);
    void updateMember(Long id, String password, String newPassword, String newPasswordCheck, String newUsername);
    void deleteMember(Long id, String password);
    void passwordValidate (String password, String inputPassword);
}
