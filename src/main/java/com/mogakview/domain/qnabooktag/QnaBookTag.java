package com.mogakview.domain.qnabooktag;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.qnabook.QnaBook;
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
public class QnaBookTag extends BaseEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qna_book_id", nullable = false)
    private QnaBook qnaBook;

    @Builder
    public QnaBookTag(QnaBook qnaBook, String name) {
        this.name = name;
        this.qnaBook = qnaBook;
    }

    public static QnaBookTag of(QnaBook qnaBook, String name) {
        return QnaBookTag.builder()
            .name(name)
            .qnaBook(qnaBook)
            .build();
    }

    public void updateQnaBook(QnaBook qnaBook) {
        this.qnaBook = qnaBook;
    }
}
