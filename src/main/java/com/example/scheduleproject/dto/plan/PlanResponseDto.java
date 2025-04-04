package com.example.scheduleproject.dto.plan;

import com.example.scheduleproject.entity.Plan;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {

    private final Long id;

    private final String title;

    private final LocalDate targetDate;

    private final LocalDateTime createdAt;

    private final String username;

    public PlanResponseDto(Long id, String title, LocalDate targetDate, LocalDateTime createdAt, String username) {
        this.id = id;
        this.title = title;
        this.targetDate = targetDate;
        this.createdAt = createdAt;
        this.username = username;
    }

    public static PlanResponseDto toDto(Plan plan){
        return new PlanResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getTargetDate(),
                plan.getCreatedAt(),
                plan.getMember().getUsername()
        );
    }
}
