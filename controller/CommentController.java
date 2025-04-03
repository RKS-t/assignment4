package com.example.scheduleproject.controller;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.comment.CommentRequestDto;
import com.example.scheduleproject.dto.comment.CommentResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/plans/{planId}")
    public ResponseEntity<CommentResponseDto> saveComment(
            @PathVariable Long planId,
            @Valid @RequestBody CommentRequestDto dto,
            HttpSession session
    ){
        MemberResponseDto loginUser = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);



    }




}
