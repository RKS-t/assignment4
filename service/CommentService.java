package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.comment.CommentRequestDto;
import com.example.scheduleproject.dto.comment.CommentResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto saveComment(Long planId, CommentRequestDto dto, MemberResponseDto loginUser);
    List<CommentResponseDto> findByPlanId(Long id);
    void updateComment(Long planId, Long id, CommentRequestDto dto);
    void deleteComment(Long planId, Long id);
}
