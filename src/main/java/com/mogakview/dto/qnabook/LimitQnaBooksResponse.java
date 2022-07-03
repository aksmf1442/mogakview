package com.mogakview.dto.qnabook;

import com.mogakview.domain.qnabook.QnaBook;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LimitQnaBooksResponse {

    private List<QnaBookResponse> qnaBooksResponses;

    public static LimitQnaBooksResponse of(List<QnaBook> qnaBooks) {
        return LimitQnaBooksResponse.builder()
            .qnaBooksResponses(qnaBooks.stream()
                .map(qnaBook -> QnaBookResponse.of(qnaBook, qnaBook.getQnaBookTags()))
                .collect(Collectors.toList()))
            .build();
    }
}
