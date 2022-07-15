package com.mogakview.infrasturcture.auth;

import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;

public interface OauthManager {

    User findUserDetailByOauthCode(String code);

    boolean checkSameSocialType(SocialType socialType);
}
