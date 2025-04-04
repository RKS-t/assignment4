package com.example.scheduleproject.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    @NotBlank
    private final String password;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,30}$",
            message = "비밀번호는 8자 이상 30자 이하이며, 영문과 숫자를 포함해야 합니다.")
    private final String newPassword;

    private final String newPasswordCheck;

    @NotBlank
    @Size(max=30)
    private final String newUsername;

    public UpdateMemberRequestDto(String password, String newPassword, String newPasswordCheck, String newUsername) {
        this.password = password;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
        this.newUsername = newUsername;
    }
}
