package com.mogakview.exception.auth;

import com.mogakview.exception.http.UnauthorizedException;
import lombok.Getter;

@Getter
public class ProfileGetApiFailedException extends UnauthorizedException {

    private final String message = "프로필 정보를 가져오는데 실패했습니다.";
}
