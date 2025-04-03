package com.example.scheduleproject.service;

import com.example.scheduleproject.config.MemberValidator;
import com.example.scheduleproject.config.PasswordEncoder;
import com.example.scheduleproject.dto.comment.CommentRequestDto;
import com.example.scheduleproject.dto.comment.CommentResponseDto;
import com.example.scheduleproject.dto.member.MemberResponseDto;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.exception.MismatchException;
import com.example.scheduleproject.repository.CommentRepository;
import com.example.scheduleproject.repository.MemberRepository;
import com.example.scheduleproject.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

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

    @Override
    public List<CommentResponseDto> findByPlanId(Long planId) {

        return commentRepository.findCommentByPlanId(planId).stream().map(CommentResponseDto::toDto).toList();
    }

    @Transactional
    @Override
    public void updateComment(Long planId, Long id, CommentRequestDto dto) {

        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        if(planId!=comment.getPlan().getId()){
            throw new MismatchException();
        }

        memberValidator.memberValidate(comment.getMember().getId());

        comment.updateComment(dto.getContents());

    }




}
