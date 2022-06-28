package com.mogakview.application.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import com.mogakview.domain.user.UserRepository;
import com.mogakview.dto.auth.AccessTokenResponse;
import com.mogakview.dto.auth.LoginRequest;
import com.mogakview.infrasturcture.auth.JwtTokenProvider;
import com.mogakview.infrasturcture.auth.OauthManager;
import com.mogakview.infrasturcture.auth.OauthManagerFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final OauthManagerFactory oauthManagerFactory;

    public AccessTokenResponse createToken(String socialType, LoginRequest loginRequest) {
        SocialType loginType = SocialType.of(socialType);
        OauthManager oauthManager = oauthManagerFactory.findOauthManagerBySocialType(loginType);
        User oauthUser = oauthManager.findUserDetailByOauthCode(loginRequest.getCode());
        Optional<User> findUser = userRepository.findBySocialIdAndSocialType(
            oauthUser.getSocialId(), oauthUser.getSocialType());

        if (findUser.isPresent()) {
            return AccessTokenResponse.of(jwtTokenProvider.createAccessToken(oauthUser.getId()));
        }

        User savedUser = userRepository.save(oauthUser);
        return AccessTokenResponse.of(jwtTokenProvider.createAccessToken(savedUser.getId()));
    }

}
