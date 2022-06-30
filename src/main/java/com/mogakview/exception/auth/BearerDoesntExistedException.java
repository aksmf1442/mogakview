package com.mogakview.exception.auth;

import lombok.Getter;

@Getter
public class BearerDoesntExistedException extends RuntimeException {

    private final String message = "Bearer 타입 토큰이 아닙니다.";
}
