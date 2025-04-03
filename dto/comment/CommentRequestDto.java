package com.example.scheduleproject.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull
    private final String contents;

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }


}
