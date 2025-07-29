package com.hockeydb.hockeydb.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtHelper {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final int MINUTES = 60;

    public static String generateToken(String email) {
        Instant now = Instant.now();

        return Jwts.builder().subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String extractUserName(String token) throws Exception {
        return getTokenBody(token).getSubject();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private static Claims getTokenBody(String token) throws Exception {
        try {
            return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new Exception(e);
        }
    }

    private static Boolean isTokenExpired(String token) throws Exception {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }
}
