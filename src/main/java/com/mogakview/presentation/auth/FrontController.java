package com.mogakview.presentation.auth;

import com.mogakview.config.auth.AppUser;
import com.mogakview.config.auth.LoginUser;
import com.mogakview.dto.auth.GoogleOauthTokenRequest;
import com.mogakview.dto.auth.OauthTokenResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private String ENDPOINT = "https://accounts.google.com/o/oauth2/auth";

    @Value("${google.client.id}")
    private String CLIENT_ID;

    private String REDIRECT_URI = "http://localhost:8080/google/callback";

    private String RESPONSE_TYPE = "code";

    private String SCOPE = "profile";

    @GetMapping("/front/code/test")
    public String getCodeByGoogle() {
        return "redirect:" + ENDPOINT + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
            +  "&scope=" + SCOPE + "&response_type=" + RESPONSE_TYPE;
    }

    @ResponseBody
    @GetMapping("/google/callback")
    public String googleCallback(String code) {
        return code;
    }

    @ResponseBody
    @GetMapping("/auth/google/callback")
    public String authGoogleCallback() {
        return "123";
    }

}
