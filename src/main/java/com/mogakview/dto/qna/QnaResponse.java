package com.mogakview.dto.qna;

import com.mogakview.domain.qna.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QnaResponse {

    private Long id;

    private String question;
    private String answer;

    public static QnaResponse of(Qna qna) {
        return QnaResponse.builder()
            .id(qna.getId())
            .question(qna.getQuestion())
            .answer(qna.getAnswer())
            .build();
    }
}
