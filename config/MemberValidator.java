package com.example.scheduleproject.config;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.exception.MisMatchMemberException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final HttpSession session;

    public void memberValidate(long id){

        MemberResponseDto loginUser = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        if(id!=loginUser.getId()){
            throw new MisMatchMemberException("작성자가 아닙니다.");
        }

    }
}
