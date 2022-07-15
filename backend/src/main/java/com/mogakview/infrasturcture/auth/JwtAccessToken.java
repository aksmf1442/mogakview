package com.mogakview.infrasturcture.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtAccessToken extends JwtToken {

    @Value("${jwt.access-token.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token.validTime}")
    private Long validTime;

}
