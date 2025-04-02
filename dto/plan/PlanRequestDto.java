package com.example.scheduleproject.dto.plan;

import com.example.scheduleproject.entity.Member;
import lombok.Getter;

@Getter
public class PlanRequestDto {

    private final String title;

    private final String contents;

    public PlanRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
