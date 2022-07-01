package com.mogakview.dto.qnabook;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.User;
import lombok.Getter;

@Getter
public class QnaBookRequest {

    private String title;

    private boolean opened;


    public QnaBook toQnaBook(User user) {
        return QnaBook.builder()
            .user(user)
            .title(title)
            .opened(opened)
            .deleted(false)
            .build();
    }
}
