package com.example.scheduleproject.dto.member;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {

    private Long id;

    private String email;

    private String username;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public MemberResponseDto(Long id, String email, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
