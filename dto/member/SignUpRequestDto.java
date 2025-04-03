package com.example.scheduleproject.dto.member;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,30}$",
            message = "비밀번호는 8자 이상 30자 이하이며, 영문과 숫자를 포함해야 합니다.")
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
