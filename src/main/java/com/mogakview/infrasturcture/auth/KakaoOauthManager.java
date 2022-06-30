package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.dto.auth.OauthTokenRequest;
import com.mogakview.dto.auth.UserDetailResponse;
import java.util.Map;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauthManager extends AbstractOauthManager {


    private final String JSON_PROPERTY_KAKAO_ACCOUNT = "kakao_account";
    private final String JSON_PROPERTY_PROFILE = "profile";
    private final String JSON_PROPERTY_ID = "id";
    private final String JSON_PROPERTY_NAME = "nickname";
    private final String JSON_PROPERTY_PICTURE = "thumbnail_image_url";

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client.grant-type}")
    private String grantType;

    @Value("${kakao.url.access-token}")
    private String accessTokenUrl;

    @Value("${kakao.url.profile}")
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
        return SocialType.KAKAO == socialType;
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
        Map<String, Object> account = (Map<String, Object>) jsonObject.get(JSON_PROPERTY_KAKAO_ACCOUNT);
        Map<String, Object> profile = (Map<String, Object>) account.get(JSON_PROPERTY_PROFILE);

        String socialId = jsonObject.get(JSON_PROPERTY_ID).toString();
        String nickname = profile.get(JSON_PROPERTY_NAME).toString();
        String thumbnailImageUrl = profile.get(JSON_PROPERTY_PICTURE).toString();
        return UserDetailResponse.of(socialId, nickname, thumbnailImageUrl);
    }
}
