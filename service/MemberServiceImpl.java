package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.UpdateMemberRequestDto;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.exception.LoginFailException;
import com.example.scheduleproject.exception.PasswordCheckFailException;
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
    public MemberResponseDto signUp(SignUpRequestDto dto) {

        if(!dto.getPassword().equals(dto.getPasswordCheck())){
            throw new PasswordCheckFailException();
        }

        Member member = new Member(dto.getEmail(), dto.getPassword(), dto.getUsername());

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

    /*특정한 회원 정보를 수정*/
    @Transactional
    @Override
    public void updateMember(Long id, UpdateMemberRequestDto dto) {

        String newPassword = dto.getNewPassword();
        String newPasswordCheck = dto.getNewPasswordCheck();
        String newUsername = dto.getNewUsername();
        Member member = memberRepository.findMemberByIdOrElseThrow(id);

        passwordValidate(member.getPassword(), dto.getPassword());
        /*
        비밀번호 변경 입력에 아무것도 없을 시 이름만 변경
        비밀번호가 입력값이 있고 확인과 같을시 비밀번호도 변경
         */
        if(newPassword == null && newPasswordCheck == null){
            member.updateUsername(newUsername);
        } else if (newPassword == null ^ newPasswordCheck == null) {
            throw new PasswordCheckFailException();
        } else if(!newPassword.equals(newPasswordCheck)){
            throw new PasswordCheckFailException();
        } else if(!newPassword.equals(member.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "동일한 비밀번호로는 바꿀 수 없습니다.");
        } else {
            member.updatePasswordAndUsername(newPassword, newUsername);
        }
    }

    //특정한 회원 정보를 삭제
    @Override
    public void deleteMember(Long id, String password) {

        Member member = memberRepository.findMemberByIdOrElseThrow(id);

        passwordValidate(member.getPassword(), password);

        memberRepository.delete(member);
    }


    /*비밀번호 검증 함수*/
    @Override
    public void passwordValidate(String password, String inputPassword) {

        if(!password.equals(inputPassword)){
            throw new LoginFailException("잘못된 비밀번호입니다.");
        }
    }


}
