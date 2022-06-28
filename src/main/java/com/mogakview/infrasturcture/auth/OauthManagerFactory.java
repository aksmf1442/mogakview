package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthManagerFactory {

    private final GoogleOauthManager googleOauthManager;

    public OauthManager findOauthManagerBySocialType(SocialType socialType) {
        if (socialType == SocialType.GOOGLE) {
            return googleOauthManager;
        }

        // custom 에러 추가할 예정
        throw new RuntimeException();
    }
}
