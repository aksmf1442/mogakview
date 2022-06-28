package com.mogakview.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponse {

    private String token;

    public static RefreshTokenResponse of(String accessToken) {
        return RefreshTokenResponse.builder()
            .token(accessToken)
            .build();
    }
}
