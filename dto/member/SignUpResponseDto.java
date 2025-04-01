package com.example.scheduleproject.dto.member;

public class SignUpResponseDto {

    private Long id;

    private String email;

    private String username;

    public SignUpResponseDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
