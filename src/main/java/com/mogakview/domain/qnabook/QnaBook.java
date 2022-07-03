package com.mogakview.domain.qnabook;

import com.mogakview.domain.BaseEntity;
import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "qnaBook", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<QnaBookTag> qnaBookTags = new ArrayList<>();

    @OneToMany(mappedBy = "qnaBook")
    private List<Qna> qnas = new ArrayList<>();

    @Builder
    public QnaBook(String title, User user, boolean opened, boolean deleted) {
        this.title = title;
        this.user = user;
        this.opened = opened;
        this.deleted = deleted;
    }

    public void updateWithQnaBookTags(String title, boolean opened, List<QnaBookTag> qnaBookTags) {
        this.title = title;
        this.opened = opened;
        updateQnaBookTags(qnaBookTags);
    }

    public void updateQnaBookTags(List<QnaBookTag> qnaBookTags) {
        this.qnaBookTags.clear();
        this.qnaBookTags.addAll(qnaBookTags);
        qnaBookTags.forEach(qnaBookTag -> qnaBookTag.updateQnaBook(this));
    }

    public void delete() {
        this.deleted = true;
        qnas.forEach(Qna::delete);
        qnaBookTags.forEach(QnaBookTag::delete);
    }
}
