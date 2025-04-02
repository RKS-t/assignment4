package com.example.scheduleproject.dto.plan;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private final String username;

    private final String email;

    public PlanResponseDto(Long id,
                           String title,
                           String contents,
                           LocalDateTime createdAt,
                           LocalDateTime modifiedAt,
                           String username,
                           String email
    ) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
        this.email = email;
    }
}
