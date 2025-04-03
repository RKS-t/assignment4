package com.example.scheduleproject.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.scheduleproject.exception.LoginAuthException;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncoder {

    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}