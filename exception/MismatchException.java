package com.example.scheduleproject.exception;

public class MismatchException extends RuntimeException {
    public MismatchException() {
        super("해당 글에 해당 댓글이 존재하지 않습니다.");
    }
}
