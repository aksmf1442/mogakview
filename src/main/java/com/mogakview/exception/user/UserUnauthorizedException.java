package com.mogakview.exception.user;

import com.mogakview.exception.http.UnauthorizedException;

public class UserUnauthorizedException extends UnauthorizedException {
    private final String message = "해당 권한이 없는 유저입니다.";
}
