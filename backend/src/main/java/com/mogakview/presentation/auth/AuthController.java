package com.mogakview.presentation.auth;

import com.mogakview.application.auth.AuthService;
import com.mogakview.dto.auth.AccessTokenResponse;
import com.mogakview.dto.auth.LoginRequest;
import com.mogakview.dto.auth.RefreshTokenResponse;
import com.mogakview.infrasturcture.auth.JwtAccessToken;
import com.mogakview.infrasturcture.auth.JwtRefreshToken;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final JwtAccessToken jwtAccessToken;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<AccessTokenResponse> login(@PathVariable String socialType,
        @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        AccessTokenResponse accessTokenResponse = authService.createToken(socialType, loginRequest);
        Long userId = authService.extractUserIdByToken(accessTokenResponse.getToken(),
            jwtAccessToken);
        createRefreshToken(userId, response);
        return ResponseEntity.ok(accessTokenResponse);
    }

    @GetMapping("/token")
    public ResponseEntity<AccessTokenResponse> createNewToken(
        @CookieValue(value = REFRESH_TOKEN_COOKIE_NAME, required = false) String refreshToken,
        HttpServletResponse response) {
        Long userId = authService.extractUserIdByToken(refreshToken, jwtRefreshToken);
        AccessTokenResponse newAccessTokenResponse = authService.createNewAccessToken(userId);
        createRefreshToken(userId, response);
        return ResponseEntity.ok(newAccessTokenResponse);
    }

    private void createRefreshToken(Long userId, HttpServletResponse response) {
        RefreshTokenResponse newRefreshTokenResponse = authService.createRefreshToken(userId);
        ResponseCookie refreshCookie = injectRefreshTokenToCookie(newRefreshTokenResponse);
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    private ResponseCookie injectRefreshTokenToCookie(RefreshTokenResponse refreshTokenResponse) {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshTokenResponse.getToken())
            .sameSite("Lax")
            // local에선 편의를 위해 꺼두기
//            .secure(true)
            .httpOnly(true)
            .path("/")
            .maxAge(jwtRefreshToken.getValidTime())
            .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        removeRefreshTokenToCookie(response);
        return ResponseEntity.noContent().build();
    }

    private void removeRefreshTokenToCookie(HttpServletResponse response) {
        ResponseCookie removeRefreshToken = ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, "")
            .sameSite("Lax")
            .secure(true)
            .httpOnly(true)
            .path("/")
            .maxAge(0)
            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, removeRefreshToken.toString());
    }
}
