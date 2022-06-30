package com.mogakview.dto.auth;

import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class KaKaoUserDetailResponse implements UserDetailResponse{

    private String userName;

    private String socialId;

    private String profileUrl;

    public static UserDetailResponse of(String id, String nickname, String thumbnailImageUrl) {
        return KaKaoUserDetailResponse.builder()
            .socialId(id)
            .userName(nickname)
            .profileUrl(thumbnailImageUrl)
            .build();
    }


    @Override
    public User toUser() {
        return User.builder()
            .socialId(socialId)
            .userName(userName)
            .profileUrl(profileUrl)
            .role(Role.USER)
            .socialType(SocialType.KAKAO)
            .build();
    }


}
