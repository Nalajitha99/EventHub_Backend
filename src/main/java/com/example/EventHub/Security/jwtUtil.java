package com.example.EventHub.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class jwtUtil {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-in-ms}")
    private int jwtExpirationInMs;

    public jwtUtil() {
    }

    private Key getSigningKey() {
        byte[] keyBytes = (byte[])Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        return this.generateToken(authentication.getName(), this.jwtExpirationInMs);
    }

    private String generateToken(String subject, int expirationTime) {
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + (long)expirationTime);
        Key key = this.getSigningKey();
        return ((JwtBuilder)((JwtBuilder)((JwtBuilder)Jwts.builder().setSubject(subject)).setIssuedAt(currentDate)).setExpiration(expiryDate)).signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = (Claims)Jwts.parser().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception var3) {
            throw new RuntimeException("Expired or invalid JWT token");
        }
    }
}
