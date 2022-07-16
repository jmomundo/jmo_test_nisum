package com.nisum.application.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final String secret;

    private JwtFilter(String secret) {
        this.secret = secret;
    }

    public static JwtFilter from(String secret) {
        return new JwtFilter(secret);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        var jwtAuthenticationToken = tokenParser(authorizationHeader, response);
        jwtAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    private JwtAuthenticationToken tokenParser(String authorizationHeader,
                                               HttpServletResponse response) {
        String token = authorizationHeader.replace(TOKEN_PREFIX, "");
        Key key = Keys.hmacShaKeyFor(secret.trim().getBytes(StandardCharsets.UTF_8));
        validateClaims(key, token, response);
        Jws<Claims> parseClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        var sessionClaims = parseClaims.getBody();
        return JwtAuthenticationToken.fromContext(constructSessionHolder(sessionClaims));
    }

    private void validateClaims(Key key, String token, HttpServletResponse response) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException |
                ExpiredJwtException | IllegalArgumentException ex) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    private Map<String, String> constructSessionHolder(Claims claims) {
        String secret = claims.get("secret", String.class);
        return Map.of("secret", secret);
    }

}
