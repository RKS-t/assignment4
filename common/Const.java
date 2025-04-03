package com.example.scheduleproject.common;

public interface Const {
    String LOGIN_USER = "loginUser";
    String[] WHITE_LIST = {
            "/",
            "/members/signup",
            "/members/login"
    };
    String[] BLOCK_LIST = {
            "/members/signup",
            "/members/login"
    };
}
