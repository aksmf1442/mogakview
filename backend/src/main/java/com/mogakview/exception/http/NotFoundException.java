package com.mogakview.exception.http;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

}
