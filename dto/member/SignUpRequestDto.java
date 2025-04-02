package com.example.scheduleproject.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 8, max = 60)
    private final String password;

    @NotBlank
    private final String passwordCheck;

    @NotBlank
    @Size(max=30)
    private final String username;

    public SignUpRequestDto(String email, String password, String passwordCheck, String username) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
    }
}
