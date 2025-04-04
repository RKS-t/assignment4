package com.example.scheduleproject.config;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.exception.MisMatchMemberException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final HttpSession session;

    public void memberValidate(long id){

        //로그인한 사용자와 작성자 매치
        MemberResponseDto loginUser = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        if(id!=loginUser.getId()){
            throw new MisMatchMemberException("작성자가 아닙니다.");
        }

    }
}
