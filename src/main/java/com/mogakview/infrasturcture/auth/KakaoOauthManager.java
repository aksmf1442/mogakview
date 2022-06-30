package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.User;
import com.mogakview.dto.auth.KaKaoOauthTokenRequest;
import com.mogakview.dto.auth.KaKaoUserDetailResponse;
import com.mogakview.dto.auth.OauthTokenRequest;
import com.mogakview.dto.auth.OauthTokenResponse;
import com.mogakview.dto.auth.UserDetailResponse;
import java.util.Map;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoOauthManager implements OauthManager{

    private final WebClient webClient = WebClient.create();

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
    public User findUserDetailByOauthCode(String code) {
        OauthTokenResponse oauthTokenResponse = getAccessTokenByOauthCode(code);
        Mono<JSONObject> userDetailResponseMono = webClient
            .get()
            .uri(profileUrl)
            .headers(header -> header.setBearerAuth(oauthTokenResponse.getAccessToken()))
            .exchangeToMono(response -> {
                if (!response.statusCode().equals(HttpStatus.OK)) {
                    // custom 에러 만들 예정
                    throw new RuntimeException();
                }
                return response.bodyToMono(JSONObject.class);
            });

        JSONObject jsonObject = userDetailResponseMono.block();
        Long id = (Long) jsonObject.get("id");
        String socialId = id.toString();
        Map<String, Object> account = (Map<String, Object>) jsonObject.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        String nickname = (String) profile.get("nickname");
        String thumbnailImageUrl = (String) profile.get("thumbnail_image_url");
        UserDetailResponse userDetailResponse = KaKaoUserDetailResponse.of(socialId, nickname, thumbnailImageUrl);
        return userDetailResponse.toUser();
    }

    private OauthTokenResponse getAccessTokenByOauthCode(String code) {
        KaKaoOauthTokenRequest oauthTokenRequest = createOauthTokenRequest(code);
        Mono<OauthTokenResponse> oauthTokenResponseMono = webClient
            .post()
            .uri(accessTokenUrl)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromFormData(
                "code", oauthTokenRequest.getCode())
                .with("client_id", oauthTokenRequest.getClientId())
                .with("client_secret", oauthTokenRequest.getClientSecret())
                .with("redirect_uri", oauthTokenRequest.getRedirectUri())
                .with("grant_type", oauthTokenRequest.getGrantType())
            )
            .exchangeToMono(response -> {
                if (!response.statusCode().equals(HttpStatus.OK)) {
                    // custom 에러 만들 예정
                    throw new RuntimeException();
                }
                return response.bodyToMono(OauthTokenResponse.class);
            });

        OauthTokenResponse oauthTokenResponse = oauthTokenResponseMono.block();
        return oauthTokenResponse;
    }

    private KaKaoOauthTokenRequest createOauthTokenRequest(String code) {
        return KaKaoOauthTokenRequest.builder()
            .code(code)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectUri(redirectUri)
            .grantType(grantType)
            .build();
    }
}
