package com.example.scheduleproject.controller;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.dto.comment.CommentRequestDto;
import com.example.scheduleproject.dto.comment.CommentResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plans/{planId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(
            @PathVariable Long planId,
            @Valid @RequestBody CommentRequestDto dto,
            HttpSession session
    ){
        MemberResponseDto loginUser = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        CommentResponseDto commentResponseDto = commentService.saveComment(planId, dto, loginUser);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findByPlanId (@PathVariable Long planId){

        List<CommentResponseDto> commentResponseDtoList = commentService.findByPlanId(planId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateComment(
            @PathVariable Long planId,
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto
    ){
        commentService.updateComment(planId, id, dto);

        return new ResponseEntity<>(Map.of("message", "댓글이 수정되었습니다."),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteComment(
            @PathVariable Long planId,
            @PathVariable Long id
    ) {

        commentService.deleteComment(planId, id);

        return new ResponseEntity<>(Map.of("message", "댓글이 삭제되었습니다."), HttpStatus.OK);

    }



}
