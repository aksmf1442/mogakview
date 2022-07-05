package com.mogakview.exception.qna;

import com.mogakview.exception.http.NotFoundException;

public class QnaNotFoundException extends NotFoundException {
    private final String message = "해당 qna가 존재하지 않습니다.";

}
