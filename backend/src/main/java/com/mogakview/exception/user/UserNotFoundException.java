package com.mogakview.exception.user;

import com.mogakview.exception.http.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    private final String message = "해당 유저가 존재하지 않습니다.";

}
