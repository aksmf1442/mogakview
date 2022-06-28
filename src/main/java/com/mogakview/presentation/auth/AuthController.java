package com.mogakview.presentation.auth;

import com.mogakview.application.auth.AuthService;
import com.mogakview.dto.auth.AccessTokenResponse;
import com.mogakview.dto.auth.LoginRequest;
import lombok.RequiredArgsConstructor;
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

    private final AuthService authService;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<AccessTokenResponse> login(@PathVariable String socialType, @RequestBody LoginRequest loginRequest) {
        AccessTokenResponse accessTokenResponse = authService.createToken(socialType, loginRequest);

        return ResponseEntity.ok(accessTokenResponse);
    }

}
