package com.mogakview.dto.qnabook;

import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.dto.qnabooktag.TagResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class QnaBookResponse {

    private Long id;

    private String author;

    private String title;

    private List<TagResponse> tags;

    public static QnaBookResponse of(QnaBook qnaBook, List<QnaBookTag> qnaBookTags) {
        return QnaBookResponse.builder()
            .id(qnaBook.getId())
            .author(qnaBook.getUser().getUserName())
            .title(qnaBook.getTitle())
            .tags(qnaBookTags.stream()
                    .map(qnaBookTag -> TagResponse.of(qnaBookTag)).collect(Collectors.toList()))
            .build();
    }
}
