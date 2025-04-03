package com.example.scheduleproject.exception;

public class SamePasswordException extends RuntimeException {
    public SamePasswordException() {
        super("동일한 비밀번호로는 바꿀 수 없습니다.");
    }
}
