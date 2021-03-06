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
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor
@Getter
@Where(clause = "deleted=false")
public class Qna extends BaseEntity {

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
    public Qna(Long id, String question, String answer, QnaBook qnaBook, boolean deleted) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.qnaBook = qnaBook;
        this.deleted = deleted;
    }

    public void update(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void delete() {
        this.deleted = true;
    }
}
