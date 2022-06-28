package com.mogakview.domain.user;

public enum SocialType {
    GOOGLE;

    public static SocialType of(String input) {
        for (SocialType socialType : values()) {
            if (socialType.name().equals(input.toUpperCase())) {
                return socialType;
            }
        }

        // Custom Exception 추가할 예정
        throw new RuntimeException();
    }
}
