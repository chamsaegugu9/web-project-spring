package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtUtils {
    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // **JWT 생성**
    public static String generateToken(String id) {
        // return Jwts.builder()
        // .setSubject(username) // 사용자 정보 저장
        // .setIssuedAt(new Date()) // 발행 시간
        // .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료
        // 시간
        // .signWith(key, SignatureAlgorithm.) // 서명 알고리즘
        // .compact();
        return Jwts.builder()
                .subject(id)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // **JWT 검증 및 파싱**
    public static Jws<Claims> parseToken(String token) {
        // return Jwts.parserBuilder()
        // .setSigningKey(key)
        // .build()
        // .parseClaimsJws(token)
        // .getBody();
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token);
    }

    // **토큰에서 사용자 이름 추출**
    public static String getId(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    // **토큰 만료 여부 확인**
    public static boolean isTokenExpired(String token) {
        return parseToken(token).getPayload().getExpiration().before(new Date());
    }
}