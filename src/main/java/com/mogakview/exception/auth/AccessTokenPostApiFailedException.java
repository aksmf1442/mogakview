package com.mogakview.exception.auth;

import com.mogakview.exception.http.UnauthorizedException;
import lombok.Getter;

@Getter
public class AccessTokenPostApiFailedException extends UnauthorizedException {

    private final String message = "엑세스 토큰을 가져오는데 실패했습니다";
}
