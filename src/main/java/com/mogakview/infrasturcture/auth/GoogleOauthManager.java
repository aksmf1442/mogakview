package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.dto.auth.OauthTokenRequest;
import com.mogakview.dto.auth.UserDetailResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOauthManager extends AbstractOauthManager {

    private final String JSON_PROPERTY_ID = "sub";
    private final String JSON_PROPERTY_NAME = "name";
    private final String JSON_PROPERTY_PICTURE = "picture";

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.client.redirect-uri}")
    private String redirectUri;

    @Value("${google.client.grant-type}")
    private String grantType;

    @Value("${google.url.access-token}")
    private String accessTokenUrl;

    @Value("${google.url.profile}")
    private String profileUrl;

    @Override
    protected String getProfile() {
        return profileUrl;
    }

    @Override
    protected String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    @Override
    public boolean checkSameSocialType(SocialType socialType) {
        return SocialType.GOOGLE == socialType;
    }

    @Override
    protected OauthTokenRequest createOauthTokenRequest(String code) {
        return OauthTokenRequest.builder()
            .code(code)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectUri(redirectUri)
            .grantType(grantType)
            .build();
    }

    @Override
    protected UserDetailResponse extractUserDetailByJson(JSONObject jsonObject) {
        String socialId = jsonObject.get(JSON_PROPERTY_ID).toString();
        String nickname = jsonObject.get(JSON_PROPERTY_NAME).toString();
        String thumbnailImageUrl = jsonObject.get(JSON_PROPERTY_PICTURE).toString();
        return UserDetailResponse.of(socialId, nickname, thumbnailImageUrl);
    }
}
