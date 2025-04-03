package com.example.scheduleproject.handler;

import com.example.scheduleproject.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    //valid 예외처리시 위반사항 모두 출력
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Null 자료 조회시 예외
    @ExceptionHandler(NullResponseException.class)
    public ResponseEntity<Map<String,String>> handleNullResponseException(NullResponseException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    //로그인 및 비밀번호 관련 예외
    @ExceptionHandler(LoginAuthException.class)
    public ResponseEntity<Map<String,String>> handleLoginAuthException(LoginAuthException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()),HttpStatus.UNAUTHORIZED);
    }

    //패스워드와 패스워드 확인 불일치 예외
    @ExceptionHandler(PasswordCheckFailException.class)
    public ResponseEntity<Map<String,String>> handlePasswordCheckFailException(PasswordCheckFailException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    //경로가 잘못되었을 때 예외
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String,String>> handleNoHandlerFoundException(NoHandlerFoundException ex){
        return new ResponseEntity<>(Map.of("error", "존재하지 않는 경로입니다."),HttpStatus.NOT_FOUND);
    }

    //요청 경로 타입이 불일치 할때 예외
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(Map.of("error", "요청한 변수" + ex.getName() + "의 형식이 올바르지 않습니다."), HttpStatus.BAD_REQUEST);
    }

    //요청 입력 타입이 불일치 할때 예외
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(Map.of("error", "요청 본문의 형식이 올바르지 않습니다."), HttpStatus.BAD_REQUEST);
    }

    //이메일이 중복되었을 때 예외
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(Map.of("error", "이미 존재하는 이메일입니다."), HttpStatus.CONFLICT);
    }

    //댓글과 계획의 아이디 불일치
    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<Map<String,String>> handleMismatchException(MismatchException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    //같은 비밀번호로 변경하려 할시 예외
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<Map<String,String>> handleSamePasswordException (SamePasswordException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    //같은 비밀번호로 변경하려 할시 예외
    @ExceptionHandler(MisMatchMemberException.class)
    public ResponseEntity<Map<String,String>> handleMisMatchMemberException (MisMatchMemberException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
