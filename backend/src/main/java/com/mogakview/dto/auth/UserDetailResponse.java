package com.mogakview.dto.auth;

import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class UserDetailResponse {

    private String socialId;

    private String userName;

    private String profileUrl;

    private SocialType socialType;

    public static UserDetailResponse of(String socialId, String nickname, String thumbnailImageUrl,
        SocialType socialType) {
        return UserDetailResponse.builder()
            .socialId(socialId)
            .userName(nickname)
            .profileUrl(thumbnailImageUrl)
            .socialType(socialType)
            .build();
    }

    public User toUser() {
        return User.builder()
            .socialId(socialId)
            .userName(userName)
            .profileUrl(profileUrl)
            .role(Role.USER)
            .socialType(socialType)
            .build();
    }
}
