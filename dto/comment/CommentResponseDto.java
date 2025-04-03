package com.example.scheduleproject.dto.comment;

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


}
