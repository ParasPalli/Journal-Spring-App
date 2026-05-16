package com.example.jounaling_app.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

  final String SECRET_KEY = "XJkPsW7e60zgkvGW79RcK/z0rJbEOaJkFsJlGqSFbh0=";
  
  public String generateToken(String userName) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userName);
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
          .claims(claims).subject(subject)
          .header().empty().add("typ", "JWT")
          .and()
          .issuedAt(new Date(System.currentTimeMillis()))
          .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
          .signWith(getSigningKey()).compact();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }
}
