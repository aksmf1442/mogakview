package com.mogakview.infrasturcture.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtAccessToken jwtAccessToken;

    private final JwtRefreshToken jwtRefreshToken;

    public String createAccessToken(Long id) {
        return createToken(id, jwtAccessToken);
    }

    public String creatRefreshToken(Long id) {
        return createToken(id, jwtRefreshToken);
    }

    private String createToken(Long id, JwtToken jwtToken) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(jwtToken.getValidTime());

        return Jwts.builder()
            .claim("id", id)
            .setIssuedAt(Timestamp.valueOf(now))
            .setExpiration(Timestamp.valueOf(expireTime))
            .signWith(SignatureAlgorithm.HS256, jwtToken.getSecretKey())
            .compact();
    }

    public Long extractAccessToken(String accessToken) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtAccessToken.getSecretKey())
            .parseClaimsJws(accessToken)
            .getBody();
        return claims.get("id", Long.class);
    }
}
