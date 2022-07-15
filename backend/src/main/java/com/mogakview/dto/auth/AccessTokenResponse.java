package com.mogakview.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenResponse {

    private String token;

    public static AccessTokenResponse of(String accessToken) {
        return AccessTokenResponse.builder()
            .token(accessToken)
            .build();
    }

}
