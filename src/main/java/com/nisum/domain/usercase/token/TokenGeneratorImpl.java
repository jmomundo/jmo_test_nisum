package com.nisum.domain.usercase.token;

import com.nisum.domain.model.session.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenGeneratorImpl implements TokenGenerator{

    private final long expirationTime;
    private final Key key;

    private String secret = "7688acb75394487f78e90bf9378608364876327cabcdef632474348dc48cd780";

    public TokenGeneratorImpl() {
        this.expirationTime = 5000L;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generate(Login login) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("secret", UUID.randomUUID());
        Claims claims = Jwts.claims(payload).setSubject(login.getEmail());
        var currentTime = Instant.now();
        Date issuedAt = Timestamp.from(currentTime);
        Instant expiredTime = currentTime.plusSeconds(expirationTime);
        Date expiration = Timestamp.from(expiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
}
