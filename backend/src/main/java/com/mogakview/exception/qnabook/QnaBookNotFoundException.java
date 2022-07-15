package com.mogakview.exception.qnabook;

import com.mogakview.exception.http.NotFoundException;

public class QnaBookNotFoundException extends NotFoundException {
    private final String message = "해당 qnaBook이 존재하지 않습니다.";

}
