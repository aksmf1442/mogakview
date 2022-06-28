package com.mogakview.infrasturcture.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mogakview.domain.user.User;
import com.mogakview.dto.auth.GoogleOauthTokenRequest;
import com.mogakview.dto.auth.GoogleUserDetailResponse;
import com.mogakview.dto.auth.OauthTokenRequest;
import com.mogakview.dto.auth.OauthTokenResponse;
import com.mogakview.dto.auth.UserDetailResponse;
import com.nimbusds.oauth2.sdk.TokenResponse;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoogleOauthManager implements OauthManager {

    private final WebClient webClient = WebClient.create();

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
    public User findUserDetailByOauthCode(String code) {
        OauthTokenResponse oauthTokenResponse = getAccessTokenByOauthCode(code);
        Mono<UserDetailResponse> userDetailResponseMono = webClient
            .get()
            .uri(profileUrl)
            .headers(header -> header.setBearerAuth(oauthTokenResponse.getAccessToken()))
            .exchangeToMono(response -> {
                // custom 에러 만들 예정
                if (!response.statusCode().equals(HttpStatus.OK)) {
                    throw new RuntimeException();
                }
                return response.bodyToMono(GoogleUserDetailResponse.class);
            });

        UserDetailResponse userDetailResponse = userDetailResponseMono.block();
        return userDetailResponse.toUser();
    }

    private OauthTokenResponse getAccessTokenByOauthCode(String code) {
        OauthTokenRequest oauthTokenRequest = createOauthTokenRequest(code);
        Mono<OauthTokenResponse> oauthTokenResponseMono = webClient
            .post()
            .uri(accessTokenUrl)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(oauthTokenRequest)
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

    private OauthTokenRequest createOauthTokenRequest(String code) {
        return GoogleOauthTokenRequest.builder()
            .code(code)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectUri(redirectUri)
            .grantType(grantType)
            .build();
    }


}
