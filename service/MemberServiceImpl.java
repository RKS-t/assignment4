package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.SignUpResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public SignUpResponseDto signUp(String email, String password, String passwordCheck, String username) {

        if(password.equals(passwordCheck)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = new Member(email, password, username);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getEmail(), savedMember.getEmail());

    }
}
