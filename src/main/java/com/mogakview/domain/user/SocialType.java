package com.mogakview.domain.user;

import com.mogakview.exception.auth.SocialTypeNotFoundException;

public enum SocialType {
    GOOGLE, KAKAO;

    public static SocialType of(String input) {
        for (SocialType socialType : values()) {
            if (socialType.name().equals(input.toUpperCase())) {
                return socialType;
            }
        }
        throw new SocialTypeNotFoundException();
    }
}
