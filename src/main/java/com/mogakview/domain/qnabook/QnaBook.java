package com.mogakview.domain.qnabook;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.user.User;
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
public class QnaBook extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private boolean opened;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    public QnaBook(String title, User user, boolean opened, boolean deleted) {
        this.title = title;
        this.user = user;
        this.opened = opened;
        this.deleted = deleted;
    }
}
