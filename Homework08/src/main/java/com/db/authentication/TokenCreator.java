package com.db.authentication;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class TokenCreator {
    public String generateToken(String email) {
        return Jwts.builder().setSubject(email).compact();
    }
}
