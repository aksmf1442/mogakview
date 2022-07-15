package com.mogakview.exception.auth;

import com.mogakview.exception.http.UnauthorizedException;
import lombok.Getter;

@Getter
public class BearerDoesntExistedException extends UnauthorizedException {

    private final String message = "Bearer 타입 토큰이 아닙니다.";
}
