package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.exception.auth.SocialTypeNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthManagerFactory {

    private final List<OauthManager> oauthManagers;

    public OauthManager findOauthManagerBySocialType(SocialType socialType) {
        return oauthManagers.stream()
            .filter(oauthManager -> oauthManager.checkSameSocialType(socialType))
            .findFirst()
            // 사용자 에러 추가 예정
            .orElseThrow(SocialTypeNotFoundException::new);
    }
}
