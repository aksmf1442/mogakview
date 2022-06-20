package com.mogakview.domain.heart;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.qnabook.QnaBook;
import com.mogakview.domain.user.User;
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
public class Heart extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_book_id", nullable = false)
    private QnaBook qnaBook;

    @Builder
    public Heart(User user, QnaBook qnaBook) {
        this.user = user;
        this.qnaBook = qnaBook;
    }
}
