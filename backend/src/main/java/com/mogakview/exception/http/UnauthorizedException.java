package com.mogakview.exception.http;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

}
