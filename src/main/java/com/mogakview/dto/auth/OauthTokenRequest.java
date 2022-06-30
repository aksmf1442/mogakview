package com.mogakview.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OauthTokenRequest {

    private String code;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String grantType;
}
