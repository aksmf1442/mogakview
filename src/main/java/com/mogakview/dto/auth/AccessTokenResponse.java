package com.mogakview.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenResponse {

    private String accessToken;

    public static AccessTokenResponse of(String accessToken) {
        return AccessTokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }

}
