package com.mogakview.dto.qnabook;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.dto.qnabooktag.QnaBookTagResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QnaBookResponse {

    private Long id;

    private String author;

    private String title;

    private List<QnaBookTagResponse> tags;

    public static QnaBookResponse of(QnaBook qnaBook, List<QnaBookTag> qnaBookTags) {
        return QnaBookResponse.builder()
            .id(qnaBook.getId())
            .author(qnaBook.getUser().getUserName())
            .title(qnaBook.getTitle())
            .tags(qnaBookTags.stream()
                    .map(QnaBookTagResponse::of).collect(Collectors.toList()))
            .build();
    }
}
