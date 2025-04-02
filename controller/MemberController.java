package com.example.scheduleproject.controller;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.LoginRequestDto;
import com.example.scheduleproject.dto.member.LoginResponseDto;
import com.example.scheduleproject.dto.member.SignUpRequestDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(@Valid @RequestBody SignUpRequestDto dto){

        MemberResponseDto memberResponseDto =
                memberService.signUp(
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getPasswordCheck(),
                        dto.getUsername()
                );

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){

        LoginResponseDto responseDto = memberService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        HttpSession session = request.getSession();

        MemberResponseDto loginUser = memberService.findById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."),HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String,String>> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(Map.of("message", "로그아웃하였습니다."),HttpStatus.OK);

    }


}
