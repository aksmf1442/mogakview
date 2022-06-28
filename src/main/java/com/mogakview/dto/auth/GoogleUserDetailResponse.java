package com.mogakview.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;

public class GoogleUserDetailResponse implements UserDetailResponse{

    @JsonProperty("name")
    private String userName;

    @JsonProperty("sub")
    private String socialId;

    @JsonProperty("picture")
    private String profileUrl;

    @Override
    public User toUser() {
        return User.builder()
            .socialId(socialId)
            .userName(userName)
            .profileUrl(profileUrl)
            .role(Role.USER)
            .socialType(SocialType.GOOGLE)
            .build();
    }
}
