package com.db.authentication;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Component;

@Component
public class TokenCreator {
    @Autowired
    CurrentDateTimeProvider timeProvider;

    public String generateToken(String email) {
        return Jwts.builder().setSubject(email).compact();
    }
}
