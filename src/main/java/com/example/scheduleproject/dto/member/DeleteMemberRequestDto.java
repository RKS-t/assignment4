package com.example.scheduleproject.dto.member;

import lombok.Getter;

@Getter
public class DeleteMemberRequestDto {

    private final String password;

    public DeleteMemberRequestDto(String password) {
        this.password = password;
    }
}
