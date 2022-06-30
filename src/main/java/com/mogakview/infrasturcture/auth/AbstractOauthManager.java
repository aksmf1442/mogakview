package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.dto.auth.OauthTokenRequest;
import com.mogakview.dto.auth.OauthTokenResponse;
import com.mogakview.dto.auth.UserDetailResponse;
import com.mogakview.exception.auth.ProfileGetApiFailedException;
import com.mogakview.exception.auth.AccessTokenPostApiFailedException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public abstract class AbstractOauthManager implements OauthManager{

    public static final String CODE = "code";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String GRANT_TYPE = "grant_type";

    private final WebClient webClient = WebClient.create();

    protected abstract String getProfile();

    protected abstract String getAccessTokenUrl();

    protected abstract OauthTokenRequest createOauthTokenRequest(String code);

    protected abstract UserDetailResponse extractUserDetailByJson(JSONObject jsonObject);

    @Override
    public abstract boolean checkSameSocialType(SocialType socialType);

    @Override
    public User findUserDetailByOauthCode(String code) {
        OauthTokenResponse oauthTokenResponse = getAccessTokenByOauthCode(code);
        Mono<JSONObject> jsonObjectMono = webClient
            .get()
            .uri(getProfile())
            .headers(header -> header.setBearerAuth(oauthTokenResponse.getAccessToken()))
            .exchangeToMono(response -> {
                if (!response.statusCode().equals(HttpStatus.OK)) {
                    throw new ProfileGetApiFailedException();
                }
                return response.bodyToMono(JSONObject.class);
            });

        JSONObject jsonObject = jsonObjectMono.block();
        UserDetailResponse userDetailResponse = extractUserDetailByJson(
            jsonObject);
        return userDetailResponse.toUser();
    }

    private OauthTokenResponse getAccessTokenByOauthCode(String code) {
        OauthTokenRequest oauthTokenRequest = createOauthTokenRequest(code);
        Mono<OauthTokenResponse> oauthTokenResponseMono = webClient
            .post()
            .uri(getAccessTokenUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromFormData(
                    CODE, oauthTokenRequest.getCode())
                .with(CLIENT_ID, oauthTokenRequest.getClientId())
                .with(CLIENT_SECRET, oauthTokenRequest.getClientSecret())
                .with(REDIRECT_URI, oauthTokenRequest.getRedirectUri())
                .with(GRANT_TYPE, oauthTokenRequest.getGrantType())
            )
            .exchangeToMono(response -> {
                if (!response.statusCode().equals(HttpStatus.OK)) {
                    // custom 에러 만들 예정
                    throw new AccessTokenPostApiFailedException();
                }
                return response.bodyToMono(OauthTokenResponse.class);
            });

        return oauthTokenResponseMono.block();
    }

}
