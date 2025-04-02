package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    //회원가입 기능
    @Override
    public MemberResponseDto signUp(String email, String password, String passwordCheck, String username) {

        if(!password.equals(passwordCheck)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = new Member(email, password, username);

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getEmail(),
                savedMember.getUsername(),
                savedMember.getCreatedAt(),
                savedMember.getModifiedAt()
        );
    }

    //특정한 회원 정보를 조회
    @Override
    public MemberResponseDto findMemberById(Long id) {

        Member member = memberRepository.findMemberByIdOrElseThrow(id);

        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getUsername(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    //로그인 기능
    @Override
    public LoginResponseDto login(String email, String password) {

        //입력받은 email로 회원정보 찾기
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);

        passwordValidate(member.getPassword(), password);

        return new LoginResponseDto(member.getId());
    }

    //특정한 회원 정보를 수정
    @Transactional
    @Override
    public void updateMember(Long id, String password, String newPassword, String newPasswordCheck, String newUsername) {

        if(!newPassword.equals(newPasswordCheck)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = memberRepository.findMemberByIdOrElseThrow(id);

        passwordValidate(member.getPassword(), password);

        member.updatePasswordAndUsername(newPassword, newUsername);

    }

    //특정한 회원 정보를 삭제
    @Override
    public void deleteMember(Long id, String password) {

        Member member = memberRepository.findMemberByIdOrElseThrow(id);

        passwordValidate(member.getPassword(), password);

        memberRepository.delete(member);
    }


    //비밀번호 검증 함수
    @Override
    public void passwordValidate(String password, String inputPassword) {

        if(!password.equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"잘못된 비밀번호입니다.");
        }

    }


}
