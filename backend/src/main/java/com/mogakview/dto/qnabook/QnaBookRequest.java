package com.mogakview.dto.qnabook;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.User;
import com.mogakview.dto.qnabooktag.QnaBookTagRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaBookRequest {

    private String title;

    private boolean opened;

    private List<QnaBookTagRequest> tags;

    public QnaBook toQnaBook(User user) {
        return QnaBook.builder()
            .user(user)
            .title(title)
            .opened(opened)
            .deleted(false)
            .build();
    }
}
