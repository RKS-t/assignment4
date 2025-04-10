package com.example.scheduleproject.dto.comment;

import com.example.scheduleproject.entity.Comment;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    private final String username;

    private final String email;

    public CommentResponseDto(Long id, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String email) {
        this.id = id;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
        this.email = email;
    }

    //entity 객체 -> dto 객체 변환
    public static CommentResponseDto toDto(Comment comment){
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
