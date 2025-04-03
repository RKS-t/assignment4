package com.example.scheduleproject.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.exception.LoginFailException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpClient;

@Component
public class AuthValidator {

    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public void matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        if(!result.verified){
            throw new LoginFailException("잘못된 비밀번호입니다.");
        }
    }

    public void memberValidate(long id, Long loginUserId){

        if(id!=loginUserId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"작성자가 아닙니다.");
        }

    }

}