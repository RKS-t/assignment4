package com.example.scheduleproject.dto.plan;

import com.example.scheduleproject.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class PlanRequestDto {

    @NotBlank
    @Size(max = 50)
    private final String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate targetDate;

    private final String contents;

    public PlanRequestDto(String title, LocalDate targetDate, String contents) {
        this.title = title;
        this.targetDate = targetDate;
        this.contents = contents;
    }
}
