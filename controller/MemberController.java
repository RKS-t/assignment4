package com.example.scheduleproject.controller;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.member.*;
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

    //회원가입 기능
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(@Valid @RequestBody SignUpRequestDto dto){

        MemberResponseDto memberResponseDto = memberService.signUp(dto);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    //특정한 회원 정보를 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        MemberResponseDto memberResponseDto = memberService.findMemberById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //세션 로그인 기능
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){

        LoginResponseDto responseDto = memberService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        HttpSession session = request.getSession();

        MemberResponseDto loginUser = memberService.findMemberById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."),HttpStatus.OK);

    }

    //로그아웃 기능
    @PostMapping("/logout")
    public ResponseEntity<Map<String,String>> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(Map.of("message", "로그아웃하였습니다."),HttpStatus.OK);

    }

    //특정한 회원 정보를 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateMember (
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberRequestDto dto
            ){

        memberService.updateMember(id, dto);

        return new ResponseEntity<>(Map.of("message", "회원정보가 수정되었습니다."),HttpStatus.OK);
    }

    //특정한 회원 정보를 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteMember(
            @PathVariable Long id,
            @Valid @RequestBody DeleteMemberRequestDto dto){

        memberService.deleteMember(id, dto.getPassword());

        return new ResponseEntity<>(Map.of("message", "회원정보가 삭제되었습니다."),HttpStatus.OK);
    }
}
