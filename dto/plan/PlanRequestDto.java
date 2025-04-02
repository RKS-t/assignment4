package com.example.scheduleproject.dto.plan;

import com.example.scheduleproject.entity.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PlanRequestDto {

    private final String title;

    private final LocalDate targetDate;

    private final String contents;

    public PlanRequestDto(String title, LocalDate targetDate, String contents) {
        this.title = title;
        this.targetDate = targetDate;
        this.contents = contents;
    }
}
