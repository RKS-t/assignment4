package com.example.scheduleproject.dto.member;

public class MemberResponseDto {

    private Long id;

    private String email;

    private String username;

    public MemberResponseDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
