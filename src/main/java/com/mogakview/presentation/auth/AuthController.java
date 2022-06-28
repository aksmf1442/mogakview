package com.mogakview.presentation.auth;

import com.mogakview.application.auth.AuthService;
import com.mogakview.dto.auth.AccessTokenResponse;
import com.mogakview.dto.auth.LoginRequest;
import com.mogakview.dto.auth.RefreshTokenResponse;
import com.mogakview.infrasturcture.auth.JwtRefreshToken;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "REFRESH_TOKEN";

    private final AuthService authService;
    private final JwtRefreshToken jwtRefreshToken;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<AccessTokenResponse> login(@PathVariable String socialType, @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        AccessTokenResponse accessTokenResponse = authService.createToken(socialType, loginRequest);
        Long id = authService.extractUserIdByAccessToken(accessTokenResponse.getToken());
        RefreshTokenResponse refreshTokenResponse = authService.createRefreshToken(id);
        ResponseCookie refreshCookie = injectRefreshTokenToCookie(refreshTokenResponse);
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        return ResponseEntity.ok(accessTokenResponse);
    }

    private ResponseCookie injectRefreshTokenToCookie(RefreshTokenResponse refreshTokenResponse) {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshTokenResponse.getToken())
            .sameSite("Lax")
            .secure(true)
            .httpOnly(true)
            .path("/")
            .maxAge(jwtRefreshToken.getValidTime())
            .build();
    }

}
