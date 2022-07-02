package com.mogakview.dto.qna;

import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qnabook.QnaBook;
import lombok.Getter;

@Getter
public class QnaRequest {

    private Long qnaBookId;
    private String question;
    private String answer;

    public Qna toQna(QnaBook qnaBook) {
        return Qna.builder()
            .qnaBook(qnaBook)
            .question(question)
            .answer(answer)
            .deleted(false)
            .build();
    }
}
