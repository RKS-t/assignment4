package com.example.scheduleproject.exception;

public class PasswordCheckFailException extends RuntimeException {
    public PasswordCheckFailException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
