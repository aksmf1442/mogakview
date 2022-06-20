package com.mogakview.domain.qna;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.qnabook.QnaBook;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class QnA extends BaseEntity {

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qna_book_id", nullable = false)
    private QnaBook qnaBook;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    public QnA(String question, String answer, QnaBook qnaBook, boolean deleted) {
        this.question = question;
        this.answer = answer;
        this.qnaBook = qnaBook;
        this.deleted = deleted;
    }
}
