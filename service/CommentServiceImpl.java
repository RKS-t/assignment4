package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.comment.CommentRequestDto;
import com.example.scheduleproject.dto.comment.CommentResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.repository.CommentRepository;
import com.example.scheduleproject.repository.MemberRepository;
import com.example.scheduleproject.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final PlanRepository planRepository;

    private final MemberRepository memberRepository;


    @Override
    public CommentResponseDto saveComment(Long planId, CommentRequestDto dto, MemberResponseDto loginUser) {

        Member member = memberRepository.findMemberByIdOrElseThrow(loginUser.getId());

        Plan plan = planRepository.findPlanByIdOrElseThrow(planId);

        Comment comment = new Comment(dto.getContents());
        comment.setMember(member);
        comment.setPlan(plan);

        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getMember().getUsername(),
                comment.getMember().getEmail()
        );
    }
}
