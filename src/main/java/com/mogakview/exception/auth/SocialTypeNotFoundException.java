package com.mogakview.exception.auth;

import com.mogakview.exception.http.NotFoundException;
import lombok.Getter;

@Getter
public class SocialTypeNotFoundException extends NotFoundException {

    private final String message = "지원되지 않는 소셜 타입입니다.";

}
