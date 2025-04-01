package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public MemberResponseDto signUp(String email, String password, String passwordCheck, String username) {

        if(password.equals(passwordCheck)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = new Member(email, password, username);

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getEmail(), savedMember.getUsername());

    }

    @Override
    public MemberResponseDto findById(Long id) {

        Member member = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(member.getId(), member.getEmail(), member.getUsername());

    }

    @Override
    public LoginResponseDto login(String email, String password) {

        Member member = memberRepository.findMemberByEmailOrElseThrow(email);

        if(!member.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"잘못된 아이디 혹은 비밀번호입니다.");
        }

        return new LoginResponseDto(member.getId());
    }


}
