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
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor
@Getter
@Where(clause = "deleted=false")
public class QnaBookTag extends BaseEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qna_book_id", nullable = false)
    private QnaBook qnaBook;

    private boolean deleted;

    @Builder
    public QnaBookTag(QnaBook qnaBook, String name, boolean deleted) {
        this.name = name;
        this.qnaBook = qnaBook;
        this.deleted = deleted;
    }

    public static QnaBookTag of(QnaBook qnaBook, String name) {
        return QnaBookTag.builder()
            .name(name)
            .qnaBook(qnaBook)
            .deleted(false)
            .build();
    }

    public void updateQnaBook(QnaBook qnaBook) {
        this.qnaBook = qnaBook;
    }

    public void delete() {
        this.deleted = true;
    }
}
