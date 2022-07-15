package com.mogakview.presentation.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private String GOOGLE_ENDPOINT = "https://accounts.google.com/o/oauth2/auth";

    private String KAKAO_ENDPOINT = "https://kauth.kakao.com/oauth/authorize";

    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${google.client.redirect-uri}")
    private String GOOGLE_REDIRECT_URI;

    @Value("${kakao.client.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    private String RESPONSE_TYPE = "code";

    private String SCOPE = "profile";

    @GetMapping("/front/code/google")
    public String getCodeByGoogle() {
        return "redirect:" + GOOGLE_ENDPOINT + "?client_id=" + GOOGLE_CLIENT_ID + "&redirect_uri="
            + GOOGLE_REDIRECT_URI  + "&scope=" + SCOPE + "&response_type=" + RESPONSE_TYPE;
    }

    @GetMapping("/front/code/kakao")
    public String getCodeByKakao() {
        return "redirect:" + KAKAO_ENDPOINT + "?client_id=" + KAKAO_CLIENT_ID + "&redirect_uri="
            + KAKAO_REDIRECT_URI + "&response_type=" + RESPONSE_TYPE;
    }

    @ResponseBody
    @GetMapping("/google/callback")
    public String googleCallback(String code) {
        return code;
    }

    @ResponseBody
    @GetMapping("/kakao/callback")
    public String kakaoCallback(String code) {
        return code;
    }

    @ResponseBody
    @GetMapping("/auth/google/callback")
    public String authGoogleCallback() {
        return "123";
    }

}
