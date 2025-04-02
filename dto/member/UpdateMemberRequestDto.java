package com.example.scheduleproject.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    @NotBlank
    private final String password;

    @NotBlank
    @Size(min = 8, max = 60)
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
