package com.mogakview.domain.qnabook;

import static org.assertj.core.api.Assertions.assertThat;

import com.mogakview.domain.qna.Qna;
import com.mogakview.domain.qnabooktag.QnaBookTag;
import com.mogakview.domain.user.Role;
import com.mogakview.domain.user.SocialType;
import com.mogakview.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QnaBookTest {

    @DisplayName("[성공] QnaBook의 태그들 업데이트")
    @Test
    public void updateQnaBookTags() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook qnaBook = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        List<QnaBookTag> qnaBookTags = new ArrayList<>();
        QnaBookTag tag1 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag1")
            .deleted(false)
            .build();
        QnaBookTag tag2 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag2")
            .deleted(false)
            .build();
        QnaBookTag tag3 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag3")
            .deleted(false)
            .build();
        qnaBookTags.add(tag1);
        qnaBookTags.add(tag2);
        qnaBookTags.add(tag3);

        //when
        qnaBook.updateQnaBookTags(qnaBookTags);

        //then
        assertThat(qnaBook.getQnaBookTags().size()).isEqualTo(3);
    }

    @DisplayName("[성공] QnaBook 수정")
    @Test
    public void updateWithQnaBookTags() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook qnaBook = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        List<QnaBookTag> qnaBookTags = new ArrayList<>();
        QnaBookTag tag1 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag1")
            .deleted(false)
            .build();
        QnaBookTag tag2 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag2")
            .deleted(false)
            .build();
        QnaBookTag tag3 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag3")
            .deleted(false)
            .build();
        qnaBookTags.add(tag1);
        qnaBookTags.add(tag2);
        qnaBookTags.add(tag3);

        String updateTitle = "updateTitle";
        boolean updateOpened = false;

        //when
        qnaBook.updateWithQnaBookTags(updateTitle, updateOpened, qnaBookTags);

        //then
        assertThat(qnaBook.getTitle()).isEqualTo(updateTitle);
        assertThat(qnaBook.isOpened()).isEqualTo(updateOpened);
        assertThat(qnaBook.getQnaBookTags().size()).isEqualTo(3);
    }

    @DisplayName("[성공] QnaBook 삭제")
    @Test
    public void delete() throws Exception {
        // given
        User user = User.builder()
            .socialType(SocialType.GOOGLE)
            .role(Role.USER)
            .profileUrl("123")
            .userName("haneul")
            .socialId("haneul")
            .build();

        QnaBook qnaBook = QnaBook.builder()
            .title("qnaBook")
            .opened(true)
            .user(user)
            .deleted(false)
            .build();

        List<QnaBookTag> qnaBookTags = new ArrayList<>();
        QnaBookTag tag1 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag1")
            .deleted(false)
            .build();
        QnaBookTag tag2 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag2")
            .deleted(false)
            .build();
        QnaBookTag tag3 = QnaBookTag.builder()
            .qnaBook(qnaBook)
            .name("tag3")
            .deleted(false)
            .build();
        qnaBookTags.add(tag1);
        qnaBookTags.add(tag2);
        qnaBookTags.add(tag3);

        Qna qna1 = Qna.builder()
            .question("question")
            .answer("answer")
            .qnaBook(qnaBook)
            .deleted(false)
            .build();
        qnaBook.getQnas().add(qna1);

        //when
        qnaBook.delete();

        //then
        assertThat(qnaBook.isDeleted()).isTrue();
        qnaBook.getQnaBookTags().forEach(qnaBookTag -> assertThat(qnaBookTag.isDeleted()).isTrue());
        qnaBook.getQnas().forEach(qna -> assertThat(qna.isDeleted()).isTrue());
    }
}
