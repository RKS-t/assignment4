package com.example.scheduleproject.dto.plan;

import com.example.scheduleproject.entity.Plan;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SinglePlanResponseDto {

    private final Long id;

    private final String title;

    private final LocalDate targetDate;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    private final String username;

    private final String email;

    public SinglePlanResponseDto(Long id,
                                 String title,
                                 LocalDate targetDate,
                                 String contents,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt,
                                 String username,
                                 String email
    ) {
        this.id = id;
        this.title = title;
        this.targetDate = targetDate;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
        this.email = email;
    }

    public static SinglePlanResponseDto toDto(Plan plan){
        return new SinglePlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getTargetDate(),
                plan.getContents(),
                plan.getCreatedAt(),
                plan.getModifiedAt(),
                plan.getMember().getUsername(),
                plan.getMember().getEmail()
        );
    }
}
