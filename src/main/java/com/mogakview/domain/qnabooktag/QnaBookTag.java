package com.mogakview.domain.qnabooktag;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.tag.Tag;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qna_book_id", nullable = false)
    private QnaBook qnaBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id", nullable = false)
    private Tag tag;

    @Builder
    public QnaBookTag(QnaBook qnaBook, Tag tag) {
        this.qnaBook = qnaBook;
        this.tag = tag;
    }
}
